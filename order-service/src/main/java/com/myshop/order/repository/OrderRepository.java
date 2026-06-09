package com.myshop.order.repository;

import com.myshop.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findByUserId(UUID userId);
}
