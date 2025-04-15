package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ProductDTO {
    @JsonProperty("ProductId")
    private Integer productId;

    @JsonProperty("Name")
    @NotBlank(message = "Ten san pham khong duoc de trong")
    @Size(max = 200, message = "Ten san pham khong duoc vuot qua 200 ky tu")
    private String name;

    @JsonProperty("Price")
    @NotNull(message = "Gia san pham khong duoc de trong")
    @PositiveOrZero(message = "Gia san pham khong duoc am")
    private BigDecimal price;

    @JsonProperty("Quantity")
    @NotNull(message = "So luong san pham khong duoc de trong")
    @PositiveOrZero(message = "So luong san pham khong duoc am")
    private Integer quantity;

    @JsonProperty("Description")
    @Size(max = 200, message = "Mo ta khong duoc vuot qua 200 ky tu")
    private String description;

    @JsonProperty("CategoryId")
    @NotNull(message = "Ma loai san pham khong duoc de trong")
    private Integer categoryId;
}
