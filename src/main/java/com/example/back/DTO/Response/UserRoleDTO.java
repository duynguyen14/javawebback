package com.example.back.DTO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UserRoleDTO {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("RoleId")
    @NotNull(message = "Ma vai tro khong duoc de trong")
    private Integer roleId;
}
