package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class SizeDTO {
    @JsonProperty("SizeId")
    private Integer sizeId;

    @JsonProperty("SizeName")
    @NotBlank(message = "Ten kich co khong duoc de trong")
    @Size(max = 20, message = "Ten kich co khong duoc vuot qua 20 ky tu")
    private String sizeName;
}
