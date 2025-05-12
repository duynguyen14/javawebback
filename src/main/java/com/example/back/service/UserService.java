package com.example.back.service;

import com.example.back.dto.request.User.UserLoginDTO;
import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.User.UserLoginResponse;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.mapper.UserMapper;
import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.repository.RoleRepository;
import com.example.back.repository.UserRepository;
import com.example.back.security.JWTUntil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    JWTUntil jwtUntil;
    public UserRegisterResponse createUser(UserRegister userRegister){
        if(userRepository.existsByEmail(userRegister.getEmail())){
            throw new AppException(ErrorCodes.USER_EXISTED);
        }
        User user = userMapper.fromUserRegisterDTO(userRegister);
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        HashSet<Role> roles =new HashSet<>();
        Role role=roleRepository.findByRoleName("USER").orElseThrow(()->new AppException(ErrorCodes.ROLE_NOT_FOUND));
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toUserRegisterResponse(user);
    }

    public UserLoginResponse loginUser(UserLoginDTO userLoginDTO){
        User user =userRepository.findByEmail(userLoginDTO.getEmail()).orElseThrow(()->new AppException(ErrorCodes.USER_NAME_OR_PASSWORD_INCORRECT));
        if(!passwordEncoder.matches(userLoginDTO.getPassword(), user.getPassword())){
            throw (new AppException(ErrorCodes.USER_NAME_OR_PASSWORD_INCORRECT));
        }
        String accessToken = jwtUntil.GenerateAccessToken(user);
        String refreshToken =null;
        return UserLoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

}
