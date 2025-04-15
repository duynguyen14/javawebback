package com.example.back.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
public class ProductSIzeDTO {
    @JsonProperty("ProductId")
    @NotNull(message = "Ma san pham khong duoc de trong")
    private Integer productId;

    @JsonProperty("SizeId")
    @NotNull(message = "Ma kich co khong duoc de trong")
    private Integer sizeId;
}
