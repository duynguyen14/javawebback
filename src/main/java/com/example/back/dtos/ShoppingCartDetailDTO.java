package com.example.back.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
public class ShoppingCartDetailDTO {
    @JsonProperty("ShoppingCartId")
    @NotNull(message = "Ma gio hang khong duoc de trong")
    private Integer shoppingCartId;

    @JsonProperty("ProductId")
    @NotNull(message = "Ma san pham khong duoc de trong")
    private Integer productId;

    @JsonProperty("Quantity")
    @NotNull(message = "So luong khong duoc de trong")
    @Positive(message = "So luong phai lon hon 0")
    private Integer quantity;

    @JsonProperty("Total")
    @NotNull(message = "Tong tien khong duoc de trong")
    @PositiveOrZero(message = "Tong tien khong duoc am")
    private BigDecimal total;
}
