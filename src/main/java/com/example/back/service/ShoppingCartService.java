package com.example.back.service;

import com.example.back.dto.request.ShoppingCart.CartRequest;
import com.example.back.dto.response.Cart.AddCartResponse;
import com.example.back.entity.*;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.repository.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ShoppingCartService {

    ProductRepository productRepository;
    SizeRepository sizeRepository;
    ProductSizeRepository productSizeRepository;
    UserRepository userRepository;
    ShoppingCartRepository shoppingCartRepository;
    ShoppingCartDetailRepository shoppingCartDetailRepository;
    @Transactional
    public AddCartResponse addCartResponse(CartRequest cartRequest){

        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        User user =userRepository.findByUserName(userName).orElseThrow(()->new AppException(ErrorCodes.USER_NOT_FOUND));
        ShoppingCart shoppingCart =shoppingCartRepository.findByUser(user).orElseGet(()->
                 shoppingCartRepository.save(ShoppingCart.builder()
                            .user(user)
                            .build())
        );
        System.out.println(cartRequest.getProductId());
        Product product = productRepository.findByProductId(cartRequest.getProductId()).orElseThrow(()-> new AppException(ErrorCodes.PRODUCT_NOT_FOUND));
        Size size = sizeRepository.findBySizeName(cartRequest.getSize()).orElseThrow(()-> new AppException(ErrorCodes.SIZE_NOT_FOUND));
        ProductSize productSize =productSizeRepository.findBySizeAndProduct(size,product).orElseThrow(()-> new AppException(ErrorCodes.PRODUCT_NOT_FOUND));
        if(productSize.getQuantity()<cartRequest.getQuantity()){
            throw new AppException(ErrorCodes.PRODUCT_QUANTITY_UNAVAILABLE);
        }
        ShoppingCartDetailId shoppingCartDetailId =ShoppingCartDetailId.builder()
                .shoppingCartId(shoppingCart.getShoppingCartId())
                .productSizeId(productSize.getId())
                .build();
        ShoppingCartDetail shoppingCartDetail =shoppingCartDetailRepository.findByProductSizeAndShoppingCart(productSize,shoppingCart).orElseGet(ShoppingCartDetail::new);
        Integer quantity = shoppingCartDetail.getQuantity()==null? cartRequest.getQuantity() : cartRequest.getQuantity()+shoppingCartDetail.getQuantity();
        shoppingCartDetail =ShoppingCartDetail.builder()
                .id(shoppingCartDetailId)
                .shoppingCart(shoppingCart)
                .productSize(productSize)
                .quantity(quantity)
                .total(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .build();
        shoppingCartDetailRepository.save(shoppingCartDetail);
        return AddCartResponse.builder()
                .quantity(quantity)
                .price(shoppingCartDetail.getTotal())
                .productId(product.getProductId())
                .productName(product.getName())
                .sizeName(size.getSizeName())
                .build();

    }

}
