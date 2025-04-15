package com.example.back.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDetailId implements Serializable {
    @Column(name = "ShoppingCartId")
    private Integer shoppingCartId;

    @Column(name = "ProductId")
    private Integer productId;

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
