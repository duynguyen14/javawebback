package com.example.back.dto.request.User;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDTO {
    @Email(message = "Email không đúng định dạng")
    @NotBlank(message = "Email không được để trống")
    String email;
    @NotBlank(message = "Mật khẩu không được để trống")
    String password;
}
