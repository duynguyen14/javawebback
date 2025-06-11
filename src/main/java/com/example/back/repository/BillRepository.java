package com.example.back.repository;

import com.example.back.entity.Bill;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

    List<Bill> getByUser(User user);
    //Số đơn hàng
    @Query("SELECT COUNT(b) FROM Bill b WHERE b.status = 'COMPLETED'")
    Long countCompletedBills();
    //
    @Query("SELECT b FROM Bill b JOIN FETCH b.user ORDER BY b.time DESC")
    List<Bill> findMostRecentBill(Pageable pageable);

    // Alternative query if you want to get recent bill by specific criteria
    @Query("SELECT b FROM Bill b JOIN FETCH b.user ORDER BY b.time DESC")
    List<Bill> findAllOrderByTimeDesc();




}
