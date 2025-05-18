package com.example.back.mapper;

import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.User.ManagementUserResponse;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.entity.Role;
import com.example.back.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import com.zaxxer.hikari.HikariDataSource;
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
    public ManagementUserResponse toManagementUserResponse(User user){
        return  ManagementUserResponse.builder()
                .dob(user.getDob())
                .status(user.getStatus()==null ?"Hoạt động" :user.getStatus())
                .gender(user.getGender()==null ? "": user.getGender())
                .email(user.getEmail())
                .userName(user.getUserName())
                .roleName(user.getRoles().stream().map(Role::getRoleName).findFirst().orElse(null))
                .build();
    }
}
