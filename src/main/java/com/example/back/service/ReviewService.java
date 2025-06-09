package com.example.back.service;

import com.example.back.dto.response.Review.ReviewDTO;
import com.example.back.entity.Review;
import com.example.back.repository.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public List<ReviewDTO> getAllReviews() {
        return reviewRepository.findAllWithUserAndProduct().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ReviewDTO updateReply(Integer reviewId, String reply) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new EntityNotFoundException("Review not found"));
        review.setReply(reply);
        reviewRepository.save(review);
        return convertToDTO(review);
    }

    private ReviewDTO convertToDTO(Review r) {
        return ReviewDTO.builder()
                .reviewId(r.getReviewId().intValue())
                .comment(r.getComment())
                .rating(r.getRating())
                .date(r.getTime())
                .userName(r.getUser() != null ? r.getUser().getUserName() : "N/A")
                .productId(r.getProduct() != null ? r.getProduct().getProductId().intValue() : null)
                .productName(r.getProduct() != null ? r.getProduct().getName() : "N/A")
                .reply(r.getReply())
                .build();
    }
}
