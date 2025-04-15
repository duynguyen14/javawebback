package com.example.back.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class UserDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("Email")
    @NotBlank(message = "Email khong duoc de trong")
    @Email(message = "Email khong dung dinh dang")
    @Size(max = 200, message = "Email khong duoc vuot qua 200 ky tu")
    private String email;

    @JsonProperty("UserName")
    @NotBlank(message = "Ten dang nhap khong duoc de trong")
    @Size(max = 200, message = "Ten dang nhap khong duoc vuot qua 200 ky tu")
    private String userName;

    @JsonProperty("Password")
    @NotBlank(message = "Mat khau khong duoc de trong")
    @Size(min = 6, max = 200, message = "Mat khau phai co it nhat 6 ky tu va khong vuot qua 200 ky tu")
    private String password;

    @JsonProperty("Status")
    @Size(max = 200, message = "Trang thai khong duoc vuot qua 200 ky tu")
    private String status;

    @JsonProperty("Gender")
    @Size(max = 50, message = "Gioi tinh khong duoc vuot qua 50 ky tu")
    private String gender;

    @JsonProperty("DOB")
    private LocalDateTime dob;
}
