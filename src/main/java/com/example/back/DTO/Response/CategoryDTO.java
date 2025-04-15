package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class CategoryDTO {

    @JsonProperty("CategoryId")
    private Integer categoryId;

    @JsonProperty("Name")
    @NotBlank(message = "Ten loai san pham khong duoc de trong")
    @Size(max = 200, message = "Ten loai san pham khong duoc vuot qua 200 ky tu")
    private String name;

    @JsonProperty("CatalogId")
    @NotNull(message = "Ma danh muc khong duoc de trong")
    private Integer catalogId;
}
