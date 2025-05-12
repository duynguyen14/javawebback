package com.example.back.repository;

import com.example.back.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @Query("SELECT DISTINCT p FROM Product p LEFT JOIN FETCH p.images")
    List<Product> findProductHome();
}
