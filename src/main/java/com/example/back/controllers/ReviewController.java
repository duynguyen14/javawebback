package com.example.back.controllers;

import com.example.back.dto.response.APIResponse;
import com.example.back.dto.response.Bill.RecentBillDTO;
import com.example.back.dto.response.Review.ReviewRespone;
import com.example.back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.key}/reviews")
@RequiredArgsConstructor
public class ReviewController {
   private final ReviewService reviewService;

    @GetMapping("/recent")
    public APIResponse<List<ReviewRespone>> getRecentReviews(
            @RequestParam(defaultValue = "10") int limit) {
            List<ReviewRespone> reviews = reviewService.getRecentReviews(limit);
            return APIResponse.<List<ReviewRespone>>builder()
                    .result(reviews)
                    .build();
    }
}
