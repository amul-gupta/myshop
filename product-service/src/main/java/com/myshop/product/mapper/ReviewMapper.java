package com.myshop.product.mapper;


import com.myshop.product.dto.ReviewDto;

import com.myshop.product.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    @Autowired
    private ProductMapper productMapper;

    //dto to entity
    public Review toEntity(ReviewDto reviewDto)
    {
        return Review.builder()
                .uuid(reviewDto.getUuid())
                .title(reviewDto.getTitle())
                .comment(reviewDto.getComment())
                .rating(reviewDto.getRating())
                .build();
    }

    //entity to dto
    public ReviewDto toDto(Review review)
    {
        return ReviewDto.builder()
                .uuid(review.getUuid())
                .title(review.getTitle())
                .comment(review.getComment())
                .rating(review.getRating())
                .productDto(productMapper.toDto(review.getProduct()))
                .build();
    }

}
