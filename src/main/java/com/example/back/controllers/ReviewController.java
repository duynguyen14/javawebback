package com.example.back.controllers;

import com.example.back.dto.response.Review.ReviewDTO;
import com.example.back.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("${api.key}/reviews")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public List<ReviewDTO> getAllReviews() {
        return reviewService.getAllReviews();
    }

    @PutMapping("/{id}/reply")
    public ResponseEntity<ReviewDTO> replyToReview(
            @PathVariable Integer id,
            @RequestBody Map<String, String> payload
    ) {
        String reply = payload.get("reply");
        ReviewDTO updatedReview = reviewService.updateReply(id, reply);
        return ResponseEntity.ok(updatedReview);
    }


}
