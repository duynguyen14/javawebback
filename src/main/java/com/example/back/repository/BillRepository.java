package com.example.back.repository;

import com.example.back.entity.Bill;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

    List<Bill> findByUser(User user);

    @Query("SELECT p.id from Bill b JOIN b.billDetails bd JOIN bd.productSize ps JOIN ps.product p WHERE b.user = :user AND b.status = :status")
    List<Integer> getProductInBillByUser(@Param("user") User user, @Param("status") String status);

    Optional<Bill> findById(Integer id);
    @Query("select b from Bill b " +
            "JOIN FETCH b.address " +
            "JOIN FETCH b.billDetails bd " +
            "JOIN FETCH bd.productSize ps "+
            "JOIN FETCH ps.product p "+
            "JOIN FETCH p.images "+
            "JOIN FETCH ps.size "+
            "WHERE b.id =:id")
    Optional<Bill> getBillDetailById(@Param("id") Integer id);
}
