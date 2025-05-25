package com.example.back.dto.response.Cart;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CartResponse {
    Integer productSizeId;
    String productName;
    String sizeName;
    Integer quantity;
    BigDecimal price;
    Set<String> images;
}
