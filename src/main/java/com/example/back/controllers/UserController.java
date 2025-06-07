package com.example.back.controllers;


import com.example.back.dto.request.User.PasswordUpdate;
import com.example.back.dto.request.User.UserLoginDTO;
import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.User.ManagementUserResponse;
import com.example.back.dto.response.User.UserLoginResponse;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.dto.response.User.UserUpdateDTO;
import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.repository.UserRepository;
import com.example.back.security.CurrentUser;
import com.example.back.security.user.UserPrincipal;
import com.example.back.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.web.bind.annotation.*;
//import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.key}")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
//@CrossOrigin(origins = "*")
public class UserController {
    UserService userService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/user/register")
    public APIResponse<UserRegisterResponse> createUser(@RequestBody @Valid UserRegister userRegister){
        return APIResponse.<UserRegisterResponse>builder()
                .result(userService.createUser(userRegister))
                .build();
    }
    @PostMapping("user/login")
    public APIResponse<UserLoginResponse> loginUser(@RequestBody @Valid UserLoginDTO userLoginDTO){
        return APIResponse.<UserLoginResponse>builder()
                .result(userService.loginUser(userLoginDTO))
                .build();
    }
    @GetMapping("/user/me")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @GetMapping("user/profile")
    public APIResponse<UserUpdateDTO> getUserProfile(){
        return APIResponse.<UserUpdateDTO>builder()
                .result(userService.getUserInformation())
                .build();
    }

    @PatchMapping("user/profile")
    public APIResponse<String> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        return APIResponse.<String>builder()
                .result(userService.updateUser(userUpdateDTO))
                .build();
    }

    @PostMapping("user/change-password")
    public APIResponse<String> updatePassword(@RequestBody @Valid PasswordUpdate passwordUpdate){
        return APIResponse.<String>builder()
                .result(userService.updatePassword(passwordUpdate))
                .build();
    }
    @GetMapping("user/list")
    public APIResponse<List<ManagementUserResponse>> listUser(){
        return APIResponse.<List<ManagementUserResponse>>builder()
                .result(userService.managementUserResponses())
                .build();
    }
}
