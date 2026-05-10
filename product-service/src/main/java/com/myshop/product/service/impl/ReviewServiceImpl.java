package com.myshop.product.service.impl;

import com.myshop.product.dto.ReviewDto;
import com.myshop.product.entity.Product;
import com.myshop.product.entity.Review;
import com.myshop.product.exception.ResourceNotFoundException;
import com.myshop.product.mapper.ReviewMapper;
import com.myshop.product.repository.ProductRepository;
import com.myshop.product.repository.ReviewRepository;
import com.myshop.product.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ReviewDto createReview(ReviewDto reviewDTO, UUID productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        Review review = reviewMapper.toEntity(reviewDTO);
        review.setProduct(product);
        Review savedReview = reviewRepository.save(review);
        return reviewMapper.toDto(savedReview);
    }

    @Override
    public ReviewDto updateReview(UUID reviewId, ReviewDto reviewDTO) {

        Review existingReview = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        existingReview.setTitle(reviewDTO.getTitle());
        existingReview.setComment(reviewDTO.getComment());
        existingReview.setRating(reviewDTO.getRating());
        Review updatedReview = reviewRepository.save(existingReview);
        return reviewMapper.toDto(updatedReview);
    }

    @Override
    public void deleteReview(UUID reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));
        reviewRepository.delete(review);
    }

    @Override
    public List<ReviewDto> getAllReviews() {

        return reviewRepository.findAll()
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDto getReviewById(UUID reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ResourceNotFoundException("Review not found"));

        return reviewMapper.toDto(review);
    }

    @Override
    public List<ReviewDto> getReviewsByProductId(UUID productId) {

        return reviewRepository.findByProductUuid(productId)
                .stream()
                .map(reviewMapper::toDto)
                .collect(Collectors.toList());
    }
}