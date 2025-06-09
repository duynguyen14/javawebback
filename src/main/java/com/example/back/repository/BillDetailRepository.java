package com.example.back.repository;

import com.example.back.entity.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillDetailRepository extends JpaRepository<BillDetail,Integer> {
}
