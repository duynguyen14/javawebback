package com.example.back.controllers;

import com.example.back.dto.request.Review.ReviewDTO;
import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Review.ReviewDetail;
import com.example.back.entity.User;
import com.example.back.enums.ErrorCodes;
import com.example.back.exception.AppException;
import com.example.back.repository.UserRepository;
import com.example.back.service.ReviewService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.key}/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ReviewController {
    ReviewService reviewService;

    @GetMapping("/check/{id}")
    public APIResponse<Boolean> checkUserReview(@PathVariable Integer id){
        return APIResponse.<Boolean>builder()
                .result(reviewService.checkUserCanReview(id))
                .build();
    }
    @PostMapping("/create/{id}")
    public APIResponse<ReviewDetail> createReview(@PathVariable Integer id, @RequestBody @Valid ReviewDTO reviewDTO){
        return APIResponse.<ReviewDetail>builder()
                .result(reviewService.addReview(id,reviewDTO))
                .build();
    }

}
