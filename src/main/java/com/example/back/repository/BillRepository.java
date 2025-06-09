package com.example.back.repository;

import com.example.back.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {

    @Query("SELECT b FROM Bill b JOIN b.billDetails bd WHERE b.user.id = :userId AND bd.product.productId = :productId")
    Optional<Bill> findBillByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

}

