package com.example.back.repository;

import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
//    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmail(String email);
    // Các hàm cần thiết tự thêm nhé

//    @Override
//    Optional<User> findByName(String  name);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
}
