package com.example.back.mapper;

import com.example.back.dto.response.Product.ProductHomeDTO;
import com.example.back.entity.Image;
import com.example.back.entity.Product;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ProductMapper {
    public ProductHomeDTO toProductHomeDTO(Product product){
        return ProductHomeDTO.builder()
                .id(product.getProductId())
                .price(product.getPrice())
                .name(product.getName())
                .quantity(product.getQuantity())
                .images(product.getImages().stream().map(Image::getImage).collect(Collectors.toList()))
                .build();
    }
}
