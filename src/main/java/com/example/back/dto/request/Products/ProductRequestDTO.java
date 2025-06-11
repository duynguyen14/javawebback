package com.example.back.dto.request.Products;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDTO {
    String name;
    BigDecimal price;
    Integer quantity;
    String description;
    Integer categoryId;
    List<ImageDTO> images;
    List<Integer> sizes;
    @Data
    public static class ImageDTO {
        String image;
    }

}
