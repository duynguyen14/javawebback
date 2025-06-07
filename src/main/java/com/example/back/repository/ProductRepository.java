package com.example.back.repository;

import com.example.back.entity.Category;
import com.example.back.entity.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p ORDER BY p.createdDate DESC")
    List<Product> findProductNew(Pageable pageable);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size", "sale"})
    @Query("SELECT distinct p FROM Product p  JOIN p.sale s WHERE s.id=1")
    List<Product> findProductSale(Pageable pageable);

    @EntityGraph(attributePaths = {"images", "category", "reviews", "productSizes"})
    @Query("SELECT p FROM Product p WHERE p.productId = :id")
    Optional<Product> findProductWithDetail(@Param("id") Integer id);

    Optional<Product> findByProductId(Integer id);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p ORDER BY p.createdDate DESC")
    List<Product> findAllProduct(Pageable pageable);

    @EntityGraph(attributePaths = {"images", "productSizes","productSizes.size",  "sale"})
    @Query("SELECT distinct p FROM Product p WHERE p.category = :category ")
    List<Product> findByCategory(@Param("category") Category category, Pageable pageable);


    List<Product> findByName(String name);
}
