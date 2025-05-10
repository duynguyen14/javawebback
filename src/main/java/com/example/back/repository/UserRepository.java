package com.example.back.repository;

import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
//    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByEmail(String email);
    // Các hàm cần thiết tự thêm nhé

}
