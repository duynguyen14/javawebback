package com.example.back.entity;

import jakarta.persistence.*;
import jakarta.persistence.Embeddable;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCartDetailId implements Serializable {
//    @Column(name = "ShoppingCartId")
    Integer shoppingCartId;

//    @Column(name = "ProductId")
    Integer productId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShoppingCartDetailId that = (ShoppingCartDetailId) o;
        return Objects.equals(shoppingCartId, that.shoppingCartId) &&
                Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shoppingCartId, productId);
    }
}
