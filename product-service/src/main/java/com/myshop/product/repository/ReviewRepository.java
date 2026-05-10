package com.myshop.product.repository;

import com.myshop.product.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<Review, UUID> {


    List<Review> findByProductUuid(UUID productId);
}
