package com.example.back.repository;

import com.example.back.entity.Product;
import com.example.back.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Set;


@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Set<Review> findByProduct(Product product);
    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.user u LEFT JOIN FETCH r.product p")
    Set<Review> findAllWithUserAndProduct();

}
