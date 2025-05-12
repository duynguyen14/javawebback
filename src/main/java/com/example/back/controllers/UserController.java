package com.example.back.controllers;


import com.example.back.dto.request.User.UserLoginDTO;
import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.User.UserLoginResponse;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.entity.Role;
import com.example.back.entity.User;
import com.example.back.service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
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
//    @GetMapping("user/myinfor")
//    public APIResponse<User> getMyInfor(){
//        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println("name "+SecurityContextHolder.getContext().getAuthentication().getName());
//            if(userDetails instanceof Jwt jwt) {
//                System.out.println("role "+jwt.getClaim("roles"));
//                List<String> roleNames =jwt.getClaim("roles");
//                User user = User.builder()
//                        .userName(jwt.getSubject())
//                        .roles(roleNames.stream().map(role->Role.builder().roleName(role).build()).collect(Collectors.toSet()))
//                        .build();
//                System.out.println("name " + user.getUserName());
////                System.out.println("role: " + user.getRoles().stream().map(role -> role.getRoleName()));
//                return APIResponse.<User>builder()
//                        .result(user)
//                        .build();
//            }
//            else {
//                throw new RuntimeException();
//            }
//
//    }
    @GetMapping("admin/demo")
    public String demo(){
        return "demo";
    }
    @GetMapping("user/demo")
    public String demo1(){
        return "user demo";
    }
}
