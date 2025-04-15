package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewDTO {
    @JsonProperty("ReviewId")
    private Integer reviewId;

    @JsonProperty("Comment")
    @Size(max = 200, message = "Binh luan khong duoc vuot qua 200 ky tu")
    private String comment;

    @JsonProperty("Rating")
    @Min(value = 1, message = "Danh gia phai tu 1 den 5 sao")
    @Max(value = 5, message = "Danh gia phai tu 1 den 5 sao")
    private Integer rating;

    @JsonProperty("Time")
    private LocalDateTime time;

    @JsonProperty("ProductId")
    @NotNull(message = "Ma san pham khong duoc de trong")
    private Integer productId;

    @JsonProperty("Id")
    @NotNull(message = "Ma nguoi dung khong duoc de trong")
    private Integer id;
}
