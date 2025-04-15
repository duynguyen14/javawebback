package com.example.back.DTO.Response;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
public class ImageDTO {
    @JsonProperty("ImageId")
    private Integer imageId;

    @JsonProperty("Image")
    @NotBlank(message = "Duong dan hinh anh khong duoc de trong")
    @Size(max = 200, message = "Duong dan hinh anh khong duoc vuot qua 200 ky tu")
    private String image;

    @JsonProperty("Description")
    @Size(max = 200, message = "Mo ta khong duoc vuot qua 200 ky tu")
    private String description;

    @JsonProperty("ProductId")
    @NotNull(message = "Ma san pham khong duoc de trong")
    private Integer productId;
}
