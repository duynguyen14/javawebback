package com.example.back.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CATALOG")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Catalog {
    @Id
    @Column(name = "CatalogId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer catalogId;

    @Column(name = "Name")
    private String name;

    @OneToMany(mappedBy = "catalog", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Category> categories = new ArrayList<>();
}

