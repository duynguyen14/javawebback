package com.example.back.entity;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "SIZE")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Size {
    @Id
    @Column(name = "SizeId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer sizeId;

    @Column(name = "SizeName")
    private String sizeName;

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products = new HashSet<>();
}
