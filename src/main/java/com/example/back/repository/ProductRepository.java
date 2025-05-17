package com.example.back.repository;

import com.example.back.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p ORDER BY p.createdDate DESC")
    List<Product> findProductNew(Pageable pageable);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size", "sale"})
    @Query("SELECT distinct p FROM Product p  JOIN p.sale s WHERE s.id=1")
    List<Product> findProductSale(Pageable pageable);

    Optional<Product> findByProductId(Integer id);

//    List<Product> findAll();
}
