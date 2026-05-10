package com.myshop.product.controller;

import com.myshop.product.dto.ReviewDto;
import com.myshop.product.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Create review for a product
    @PostMapping("/product/{productId}")
    public ResponseEntity<ReviewDto> createReview(@RequestBody ReviewDto reviewDto,
                                                  @PathVariable UUID productId) {

        ReviewDto createdReview = reviewService.createReview(reviewDto, productId);
        return new ResponseEntity<>(createdReview, HttpStatus.CREATED);
    }

    // Update review
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable UUID reviewId,
                                                  @RequestBody ReviewDto reviewDto) {

        ReviewDto updatedReview = reviewService.updateReview(reviewId, reviewDto);
        return ResponseEntity.ok(updatedReview);
    }

    // Delete review
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable UUID reviewId) {

        reviewService.deleteReview(reviewId);
        return ResponseEntity.ok("Review deleted successfully");
    }

    // Get all reviews
    @GetMapping
    public ResponseEntity<List<ReviewDto>> getAllReviews() {

        return ResponseEntity.ok(reviewService.getAllReviews());
    }

    // Get review by id
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable UUID reviewId) {

        return ResponseEntity.ok(reviewService.getReviewById(reviewId));
    }

    // Get reviews by product id
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<ReviewDto>> getReviewsByProductId(@PathVariable UUID productId) {

        return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
    }
}