package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.service.OpenAiService;
import com.example.back.service.QdrantService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.key}/openai")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OpenAiController {
    OpenAiService openAIService;
    QdrantService qdrantService;

    @PostMapping("/ask")
    public APIResponse<?> ask(@RequestBody Map<String, String> request) {
        String question = request.get("question");
        // Validation
        if (question == null || question.trim().isEmpty()) {
            return APIResponse.<String>builder()
                    .code(400)
                    .message("Question must not be empty!")
                    .result(null)
                    .build();
        }

        try {
            // Tạo embedding cho câu hỏi
            List<Float> questionEmbedding = openAIService.createEmbedding(question);

            // Tìm kiếm trong Qdrant với độ chính xác cao hơn
            var results = qdrantService.search("foxystore", questionEmbedding, 5);

            if (results.isEmpty()) {
                return APIResponse.success(Map.of("answer",
                        "Xin lỗi, tôi không tìm thấy sản phẩm phù hợp với câu hỏi của bạn. " +
                                "Bạn có thể thử hỏi về sản phẩm cụ thể hoặc danh mục sản phẩm khác không?"));
            }

            // Tạo context phong phú hơn
            String context = buildEnhancedContext(results);

            // Tạo prompt thông minh hơn
            String prompt = buildSmartPrompt(question, context);

            // Lấy câu trả lời từ OpenAI
            String answer = openAIService.chatCompletion(prompt);

            return APIResponse.success(Map.of(
                    "answer", answer,
                    "products_found", results.size()
            ));

        } catch (Exception e) {
            log.error("Error processing question: {}", question, e);
            return APIResponse.<String>builder()
                    .code(500)
                    .message("Đã xảy ra lỗi khi xử lý câu hỏi của bạn. Vui lòng thử lại.")
                    .result(null)
                    .build();
        }
    }

    private String buildEnhancedContext(List<Map<String, Object>> results) {
        StringBuilder context = new StringBuilder();

        for (int i = 0; i < results.size(); i++) {
            Map<String, Object> point = results.get(i);
            Map<String, Object> payload = (Map<String, Object>) point.get("payload");

            // Thông tin cơ bản
            String name = (String) payload.get("name");
            String description = (String) payload.get("description");
            Object priceObj = payload.get("price");
            Double price = priceObj instanceof Number ? ((Number) priceObj).doubleValue() : null;
            Integer quantity = (Integer) payload.get("quantity");

            // Thông tin danh mục
            String categoryName = (String) payload.getOrDefault("category_name", "Không xác định");
            String catalogName = (String) payload.getOrDefault("catalog_name", "");

            // Thông tin đánh giá
            Object avgRatingObj = payload.get("avg_rating");
            Double avgRating = avgRatingObj instanceof Number ? ((Number) avgRatingObj).doubleValue() : 0.0;
            Integer reviewCount = (Integer) payload.getOrDefault("review_count", 0);

            // Thông tin bán hàng
            Integer totalSold = (Integer) payload.getOrDefault("total_sold", 0);
            String stockStatus = (String) payload.getOrDefault("stock_status", "unknown");
            String popularity = (String) payload.getOrDefault("popularity", "new_product");

            // Size available
            @SuppressWarnings("unchecked")
            List<String> sizes = (List<String>) payload.get("available_sizes");

            // Xây dựng thông tin sản phẩm
            context.append("=== SẢN PHẨM ").append(i + 1).append(" ===\n");
            context.append("Tên: ").append(name).append("\n");
            context.append("Giá: ").append(price != null ? String.format("%.0f VND", price) : "Chưa cập nhật").append("\n");
            context.append("Danh mục: ").append(categoryName);
            if (!catalogName.isEmpty()) {
                context.append(" (").append(catalogName).append(")");
            }
            context.append("\n");

            // Tình trạng kho
            context.append("Tình trạng: ");
            switch (stockStatus) {
                case "out_of_stock" -> context.append("Hết hàng");
                case "low_stock" -> context.append("Sắp hết hàng (còn ").append(quantity).append(" sản phẩm)");
                case "limited_stock" -> context.append("Số lượng có hạn (còn ").append(quantity).append(" sản phẩm)");
                default -> context.append("Còn hàng (").append(quantity).append(" sản phẩm)");
            }
            context.append("\n");

            // Đánh giá và độ phổ biến
            if (reviewCount > 0) {
                context.append("Đánh giá: ").append(String.format("%.1f/5 sao", avgRating))
                        .append(" (").append(reviewCount).append(" đánh giá)\n");
            }

            if (totalSold > 0) {
                context.append("Đã bán: ").append(totalSold).append(" sản phẩm\n");
            }

            // Độ phổ biến
            String popularityText = switch (popularity) {
                case "bestseller" -> "Sản phẩm bán chạy nhất";
                case "popular" -> "Sản phẩm phổ biến";
                case "highly_rated" -> "Sản phẩm được đánh giá cao";
                case "selling_well" -> "Sản phẩm bán tốt";
                default -> "Sản phẩm mới";
            };
            context.append("Trạng thái: ").append(popularityText).append("\n");

            // Size
            if (sizes != null && !sizes.isEmpty()) {
                context.append("Kích thước có sẵn: ").append(String.join(", ", sizes)).append("\n");
            }

            // Mô tả
            if (description != null && !description.trim().isEmpty()) {
                context.append("Mô tả: ").append(description).append("\n");
            }

            context.append("\n");
        }

        return context.toString();
    }

    private String buildSmartPrompt(String question, String context) {
        return String.format("""
            Bạn là FoxyBot - trợ lý mua sắm thông minh của cửa hàng FoxyStore. 
            
            NHIỆM VỤ: Tư vấn sản phẩm một cách chuyên nghiệp, thân thiện và hữu ích.
            
            NGUYÊN TẮC TƒỤ VẤN:
            1. Luôn đưa ra lời khuyên dựa trên thông tin sản phẩm cụ thể
            2. So sánh các sản phẩm khi có nhiều lựa chọn
            3. Đề xuất sản phẩm phù hợp nhất với nhu cầu khách hàng
            4. Thông báo tình trạng kho hàng và khuyến khích mua nếu sắp hết
            5. Đề cập đến đánh giá và độ phổ biến của sản phẩm
            6. Hỏi thêm thông tin nếu cần để tư vấn tốt hơn
            
            CÂU HỎI KHÁCH HÀNG: %s
            
            THÔNG TIN SẢN PHẨM LIÊN QUAN:
            %s
            
            Hãy trả lời một cách tự nhiên, chuyên nghiệp và hữu ích. Nếu có nhiều sản phẩm, 
            hãy so sánh và đề xuất sản phẩm tốt nhất. Đừng quên đề cập đến ưu điểm nổi bật 
            và lý do tại sao nên chọn sản phẩm đó.
            """, question, context);
    }
}