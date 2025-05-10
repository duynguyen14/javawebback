package com.example.back.mapper;

import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public  User fromUserRegisterDTO(UserRegister userRegister){
        return User.builder()
                .email(userRegister.getEmail())
                .userName(userRegister.getUserName())
                .password(userRegister.getPassword())
                .build();
    }
    public  UserRegisterResponse toUserRegisterResponse(User user){
        return  UserRegisterResponse.builder()
                .email(user.getEmail())
                .userName(user.getUserName())
                .build();
    }
}
