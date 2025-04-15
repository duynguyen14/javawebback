package com.example.back.DTO.Response;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
public class AddressDTO {
    @JsonProperty("AddressId")
    private Integer addressId;

    @JsonProperty("City")
    @NotBlank(message = "Thanh pho khong duoc de trong")
    @Size(max = 100, message = "Ten thanh pho khong duoc vuot qua 100 ky tu")
    private String city;

    @JsonProperty("Street")
    @NotBlank(message = "Duong pho khong duoc de trong")
    @Size(max = 200, message = "Ten duong pho khong duoc vuot qua 200 ky tu")
    private String street;

    @JsonProperty("DetailedAddress")
    @NotBlank(message = "Dia chi chi tiet khong duoc de trong")
    @Size(max = 200, message = "Dia chi chi tiet khong duoc vuot qua 200 ky tu")
    private String detailedAddress;

    @JsonProperty("Name")
    @NotBlank(message = "Ten nguoi nhan khong duoc de trong")
    @Size(max = 200, message = "Ten nguoi nhan khong duoc vuot qua 200 ky tu")
    private String name;

    @JsonProperty("PhoneNumber")
    @NotNull(message = "So dien thoai khong duoc de trong")
    @Positive(message = "So dien thoai khong hop le")
    private Integer phoneNumber;

    @JsonProperty("Id")
    @NotNull(message = "Ma nguoi dung khong duoc de trong")
    private Integer id;

}
