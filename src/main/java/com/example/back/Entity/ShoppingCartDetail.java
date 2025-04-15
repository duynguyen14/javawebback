package com.example.back.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Entity
@Table(name = "SHOPPINGCARTDETAIL")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartDetail {
    @EmbeddedId
    private ShoppingCartDetailId id;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Total")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "ShoppingCartId")
    private ShoppingCart shoppingCart;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    @JoinColumn(name = "ProductId")
    private Product product;
}
