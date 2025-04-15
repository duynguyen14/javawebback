package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.util.Date;

@Data
@Builder
public class BillDTO {
    @JsonProperty("BillId")
    @NotNull(message = "Ma hoa don khong duoc de trong")
    private Integer billId;

    @JsonProperty("ProductId")
    @NotNull(message = "Ma san pham khong duoc de trong")
    private Integer productId;

    @JsonProperty("Quantity")
    @NotNull(message = "So luong khong duoc de trong")
    @Positive(message = "So luong phai lon hon 0")
    private Integer quantity;
}
