package com.example.back.mapper;

import com.example.back.dto.response.Review.ReviewDetail;
import com.example.back.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {
    public ReviewDetail toReviewDetail(Review review){
        return  ReviewDetail.builder()
                .id(review.getReviewId())
                .comment(review.getComment())
                .rating(review.getRating())
                .date(review.getTime())
                .userName(review.getUser().getUserName())
                .build();
    }
}
