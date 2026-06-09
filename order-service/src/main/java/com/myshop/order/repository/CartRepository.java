package com.myshop.order.repository;

import com.myshop.order.entity.Cart;
import com.myshop.order.entity.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByUserId(UUID userId);

    Optional<Cart> findByUserIdAndCartStatus(UUID userId, CartStatus cartStatus);


}