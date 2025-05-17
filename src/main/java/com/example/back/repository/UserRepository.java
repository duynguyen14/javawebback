package com.example.back.repository;

import com.example.back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);
    Optional<User> findByUserName(String userName);
//    List<User> fillAll();

}
