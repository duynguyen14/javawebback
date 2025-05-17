package com.example.back.mapper;

import com.example.back.dto.response.Product.ProductDetail;
import com.example.back.dto.response.Product.ProductHome;
import com.example.back.dto.response.Product.ProductSizeDTO;
import com.example.back.dto.response.Review.ReviewDetail;
import com.example.back.entity.Image;
import com.example.back.entity.Product;
import com.example.back.entity.ProductSize;
import com.example.back.entity.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    @Autowired
    ReviewMapper reviewMapper;

    public ProductHome toProductHomeDTO(Product product){
        return ProductHome.builder()
                .id(product.getProductId())
                .price(product.getPrice())
                .name(product.getName())
                .quantity(product.getQuantity())
                .soldCount(product.getSoldCount())
                .images(product.getImages().stream().map(Image::getImage).collect(Collectors.toList()))
                .productSizeDTOS(product.getProductSizes().stream().map(ps->
                                ProductSizeDTO.builder()
                                        .sizeName(ps.getSize().getSizeName())
                                        .quantity(ps.getQuantity())
                                        .build()
                        ).collect(Collectors.toSet()))
                .discountPercent(product.getSale()!=null ? product.getSale().getDiscountPercent():null)
                .build();
    }
    public ProductDetail toProductDetail(Product product){

        List<ReviewDetail> reviewDetails =product.getReviews().stream().map(review->reviewMapper.toReviewDetail(review)).toList();

        return ProductDetail.builder()
                .id(product.getProductId())
                .name(product.getName())
                .categoryName(product.getCategory().getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .soldCount(product.getSoldCount())
                .quantity(product.getQuantity())
                .images(product.getImages().stream().map(Image::getImage).toList())
                .productSizeDTOS(product.getProductSizes().stream().map(ps->
                        ProductSizeDTO.builder()
                                .sizeName(ps.getSize().getSizeName())
                                .quantity(ps.getQuantity())
                                .build()
                ).collect(Collectors.toSet()))
                .reviews(reviewDetails)
                .build();
    }
}
