package com.example.back.repository;

import com.example.back.entity.Product;
import com.example.back.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {
    Set<Review> findByProduct(Product product);


}
