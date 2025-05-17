package com.example.back.dto.response.Cart;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class AddCartResponse {
    Integer productId;
    String productName;
    String sizeName;
    Integer quantity;
    BigDecimal price;
}
