package com.example.back.Entity;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "PRODUCT")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @Column(name = "ProductId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private BigDecimal price;

    @Column(name = "Quantity")
    private Integer quantity;

    @Column(name = "Description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CategoryId")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BillDetail> billDetails = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingCartDetail> cartDetails = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "PRODUCT_SIZE",
            joinColumns = @JoinColumn(name = "ProductId"),
            inverseJoinColumns = @JoinColumn(name = "SizeId")
    )
    private Set<Size> sizes = new HashSet<>();
}
