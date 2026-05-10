package com.myshop.product.service;

import com.myshop.product.dto.ReviewDto;

import java.util.List;
import java.util.UUID;

public interface ReviewService {

        // Create
        ReviewDto createReview(ReviewDto reviewDTO, UUID productId);

        // Update
        ReviewDto updateReview(UUID reviewId, ReviewDto reviewDTO);

        // Delete
        void deleteReview(UUID reviewId);

        // Get all reviews
        List<ReviewDto> getAllReviews();

        // Get review by id
        ReviewDto getReviewById(UUID reviewId);

        // Get reviews by product id
        List<ReviewDto> getReviewsByProductId(UUID productId);

}
