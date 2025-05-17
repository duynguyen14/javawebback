package com.example.back.repository;

import com.example.back.entity.Product;
import com.example.back.entity.ProductSize;
import com.example.back.entity.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductSizeRepository extends JpaRepository<ProductSize,Integer> {
    Optional<ProductSize> findBySizeAndProduct(Size size, Product product);
}
