package com.example.back.controllers;

import com.example.back.dto.request.ShoppingCart.CartRequest;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Cart.AddCartResponse;
import com.example.back.service.ShoppingCartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.key}/")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ShoppingCartController {

    ShoppingCartService shoppingCartService;

    @PostMapping("cart")
    public APIResponse<AddCartResponse> addCartResponseAPIResponse(@RequestBody CartRequest cartRequest){
        System.out.println(cartRequest.getProductId());
        System.out.println(cartRequest.getQuantity());
        System.out.println(cartRequest.getSize());
        return APIResponse.<AddCartResponse>builder()
                .result(shoppingCartService.addCartResponse(cartRequest))
                .build();
    }
    @GetMapping("cart")
    public APIResponse<List<AddCartResponse>> getCartResponse(){
        return  APIResponse.<List<AddCartResponse>>builder()
                .result(shoppingCartService.getCart())
                .build();
    }
}
