package com.example.back.service;



import com.example.back.dto.response.Review.ReviewRespone;
import com.example.back.entity.Review;
import com.example.back.repository.ReviewRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ReviewService {
    private final ReviewRepository reviewRepository;
    public List<ReviewRespone> getRecentReviews(int limit) {
        try {
            Pageable pageable = PageRequest.of(0, limit);
            List<Review> reviews = reviewRepository.findRecentReviews(pageable);

            if (reviews.isEmpty()) {
                throw new RuntimeException("Không có đánh giá nào gần đây");
            }

            return reviews.stream()
                    .map(review -> ReviewRespone.builder()
                            .customerName(review.getUser().getUserName())
                            .rating(review.getRating())
                            .reviewDate(review.getTime())
                            .reviewContent(review.getComment())
                            .productName(review.getProduct().getName())
                            .build())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy đánh giá gần đây: " + e.getMessage(), e);
        }
    }



}
