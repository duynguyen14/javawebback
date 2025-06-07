package com.example.back.service;

import com.example.back.dto.request.Review.ReviewDTO;
import com.example.back.dto.response.Review.ReviewDetail;
import com.example.back.entity.*;
import com.example.back.enums.BillStatus;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.mapper.ReviewMapper;
import com.example.back.repository.BillRepository;
import com.example.back.repository.ProductRepository;
import com.example.back.repository.ReviewRepository;
import com.example.back.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ReviewService {
    ReviewRepository reviewRepository;
    UserRepository userRepository;
    BillRepository billRepository;
    ProductRepository productRepository;
    ReviewMapper reviewMapper;
    private User getCurrentUser(){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUserName(userName).orElseThrow(()-> new AppException(ErrorCodes.USER_NOT_FOUND));
    }
    public Boolean checkUserCanReview(Integer id){
        User user =getCurrentUser();
        String status = BillStatus.SHIPPED.getLabel();
        List<Integer> productIds =billRepository.getProductInBillByUser(user,status);
        for(Integer productId : productIds){
            if(productId.equals(id)){
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
    @Transactional
    public ReviewDetail addReview(Integer id, ReviewDTO reviewDTO){
        User user =getCurrentUser();
        Product product =productRepository.findByProductId(id).orElseThrow(()-> new AppException(ErrorCodes.PRODUCT_NOT_FOUND));
        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        reviewMapper.mapToReviewFromReviewDTO(reviewDTO,review);
        reviewRepository.save(review);
        return reviewMapper.toReviewDetail(review);
    }
}
