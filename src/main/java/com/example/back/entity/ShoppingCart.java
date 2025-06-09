package com.example.back.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SHOPPINGCART")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShoppingCartId")
    Integer shoppingCartId;

    @Column(name = "Description")
    String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
        List<ShoppingCartDetail> cartDetails = new ArrayList<>();
}