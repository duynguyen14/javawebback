package com.example.back.Entity;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartDetail {
    Integer shoppingCartId;
    Integer productId;
    Integer quantity;
    double total;
}
