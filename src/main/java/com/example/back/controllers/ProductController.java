package com.example.back.controllers;

import com.example.back.dto.request.Products.ProductRequestDTO;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Product.*;
import com.example.back.service.ProductService;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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
    @GetMapping("products")
    public APIResponse<List<ProductHome>> getAllProduct(
            @RequestParam(defaultValue = "0") int page
    ){
        return  APIResponse.<List<ProductHome>>builder()
                .result(productService.getAllProduct(page))
                .build();
    }
    @GetMapping("category/{id}")
    public APIResponse<List<ProductHome>> getByCategory(@PathVariable Integer id ,
                                                        @RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(required = false, defaultValue = "newest") String sort
    ){
        return APIResponse.<List<ProductHome>>builder()
                .result(productService.getByCategoryId(id,page,sort))
                .build();
    }
    //admin
    @GetMapping("products/tops")
    public APIResponse<List<TopProductDTO>> getFeaturedProducts() {
        return APIResponse.<List<TopProductDTO>>builder()
                .result(productService.getTopRatedProducts())
                .build();
    }
    @GetMapping("products/search")
    public APIResponse<Page<ProductInfoDTO>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) BigDecimal minPrice,
            @RequestParam(required = false) BigDecimal maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProductInfoDTO> result = productService.searchProducts(name, categoryId, minPrice, maxPrice, pageable);
        if (result.isEmpty()) {
            return APIResponse.<Page<ProductInfoDTO>>builder()
                    .code(404)
                    .build();
        }
        return APIResponse.<Page<ProductInfoDTO>>builder()
                .code(1000)
                .result(result)
                .build();
    }
    // add


}
