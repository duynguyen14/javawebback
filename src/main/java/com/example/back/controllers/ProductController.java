package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Product.ProductHomeDTO;
import com.example.back.entity.Product;
import com.example.back.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.key}/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping("products")
    public APIResponse<List<ProductHomeDTO>> products(){
        return APIResponse.<List<ProductHomeDTO>>builder()
                .result(productService.getProductHome())
                .build();
    }
}
