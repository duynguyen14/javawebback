package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class RoleDTO {
    @JsonProperty("RoleId")
    private Integer roleId;

    @JsonProperty("RoleName")
    @NotBlank(message = "Ten vai tro khong duoc de trong")
    @Size(max = 200, message = "Ten vai tro khong duoc vuot qua 200 ky tu")
    private String roleName;

    @JsonProperty("Description")
    @Size(max = 200, message = "Mo ta khong duoc vuot qua 200 ky tu")
    private String description;
}
