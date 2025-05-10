package com.example.back.controllers;


import com.example.back.dto.request.User.UserRegister;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.User.UserRegisterResponse;
import com.example.back.Service.UserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.key}/user")
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
//@CrossOrigin(origins = "*")
public class UserController {
    UserService userService;
//    @PostMapping("")
//    public ResponseEntity<?> createUser(
//            @Valid @RequestBody UserDTO userDTO,
//            BindingResult result) {
//        if (result.hasErrors()) {
//            // Lấy tất cả các lỗi từ BindingResult
//            List<String> errorMessages = result.getAllErrors()
//                    .stream()
//                    .map(DefaultMessageSourceResolvable::getDefaultMessage)  // Lấy thông báo mặc đinhj
//                    .collect(Collectors.toList());  // Dùng collect để thu thập các phần tử vào danh sách
//            return ResponseEntity.badRequest().body(errorMessages);
//        }
//        return ResponseEntity.ok("Create new user successfully : " + userDTO);
//    }

    @PostMapping("/register")
    public APIResponse<UserRegisterResponse> createUser(@RequestBody @Valid UserRegister userRegister){
        return APIResponse.<UserRegisterResponse>builder()
                .result(userService.createUser(userRegister))
                .build();
    }
    @GetMapping("/demo")
    public APIResponse<String> demo(){
        return APIResponse.<String>builder()
                .result("hello world")
                .build();
    }
}
