package com.example.back.controllers;


import com.example.back.dtos.UserDTO;
import jakarta.validation.Valid;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.key}/users")
public class UserController {

    @PostMapping("")
    public ResponseEntity<?> createUser(
            @Valid @RequestBody UserDTO userDTO,
            BindingResult result) {
        if (result.hasErrors()) {
            // Lấy tất cả các lỗi từ BindingResult
            List<String> errorMessages = result.getAllErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)  // Lấy thông báo mặc đinhj
                    .collect(Collectors.toList());  // Dùng collect để thu thập các phần tử vào danh sách
            return ResponseEntity.badRequest().body(errorMessages);
        }
        return ResponseEntity.ok("Create new user successfully : " + userDTO);
    }
}
