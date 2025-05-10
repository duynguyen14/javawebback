package com.example.back.Service;

import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.mapper.UserMapper;
import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.repository.RoleRepository;
import com.example.back.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    RoleRepository roleRepository;
    public UserRegisterResponse createUser(UserRegister userRegister){
        if(userRepository.existsByEmail(userRegister.getEmail())){
            throw new AppException(ErrorCodes.USER_EXISTED);
        }
        User user = userMapper.fromUserRegisterDTO(userRegister);
        user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        List<Role> roles =new ArrayList<>();
        Role role=roleRepository.findByRoleName("USER").orElseThrow(()->new AppException(ErrorCodes.ROLE_NOT_FOUND));
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(user);
        return userMapper.toUserRegisterResponse(user);
    }
}
