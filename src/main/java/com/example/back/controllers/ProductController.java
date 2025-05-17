package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Product.HomeResponseDTO;
import com.example.back.dto.response.Product.ProductDetail;
import com.example.back.dto.response.Product.ProductHome;
import com.example.back.service.ProductService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.key}/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ProductController {

    ProductService productService;

    @GetMapping("products/home")
    public APIResponse<HomeResponseDTO> products(){
        return APIResponse.<HomeResponseDTO>builder()
                .result(productService.getProductHome())
                .build();
    }

    @GetMapping("product/{id}")
    public APIResponse<ProductDetail> getProductDetail(@PathVariable Integer id){
        return APIResponse.<ProductDetail>builder()
                .result(productService.getProductDetail(id))
                .build();
    }
}
