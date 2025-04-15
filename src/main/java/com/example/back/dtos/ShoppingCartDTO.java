package com.example.back.dtos;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class ShoppingCartDTO {
    @JsonProperty("ShoppingCartId")
    private Integer shoppingCartId;

    @JsonProperty("Description")
    @Size(max = 200, message = "Mo ta khong duoc vuot qua 200 ky tu")
    private String description;

    @JsonProperty("Id")
    @NotNull(message = "Ma nguoi dung khong duoc de trong")
    private Integer id;
}
