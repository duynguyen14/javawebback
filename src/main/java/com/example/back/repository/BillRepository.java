package com.example.back.repository;

import com.example.back.entity.Bill;
import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill,Integer> {

    List<Bill> getByUser(User user);
}
