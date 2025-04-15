package com.example.back.Entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SHOPPINGCART")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ShoppingCartId")
    private Integer shoppingCartId;

    @Column(name = "Description")
    private String description;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id")
    private User user;

    @OneToMany(mappedBy = "shoppingCart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCartDetail> cartDetails = new ArrayList<>();
}