package com.example.back.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {
    @Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;

    @Column(name = "Name")
    String name;

    @Column(name = "Price")
    BigDecimal price;

    @Column(name = "Quantity")
    Integer quantity;

    @Column(name = "Description")
    String description;

    @Column(name = "CreatedDate")
    LocalDateTime createdDate;

    @Column(name = "SoldCount")
    Integer soldCount;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId")
    Category category;

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Image> images = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<Review> reviews = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<BillDetail> billDetails = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<ShoppingCartDetail> cartDetails = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PRODUCT_SIZE",
            joinColumns = @JoinColumn(name = "ProductId"),
            inverseJoinColumns = @JoinColumn(name = "SizeId")
    )
    Set<Size> sizes = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    List<FavoriteProduct> favoriteProducts;
}
