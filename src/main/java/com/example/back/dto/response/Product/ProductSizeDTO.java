package com.example.back.dto.response.Product;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductSizeDTO {
    Integer productSizeId;
    String sizeName;
    Integer quantity;

}
