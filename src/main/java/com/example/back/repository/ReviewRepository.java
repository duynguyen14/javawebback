package com.example.back.repository;

import com.example.back.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("SELECT r FROM Review r LEFT JOIN FETCH r.user u LEFT JOIN FETCH r.product p")
    List<Review> findAllWithUserAndProduct();

}
