package com.myshop.order.controller;

import com.myshop.order.dto.CreateOrderRequest;
import com.myshop.order.dto.OrderResponse;
import com.myshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(
            @RequestBody CreateOrderRequest request
    ) {
        return orderService.createOrder(request);
    }

    @PutMapping("/{orderId}/cancel")
    public OrderResponse cancelOrder(
            @PathVariable UUID orderId
    ) {
        return orderService.cancelOrder(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrderById(
            @PathVariable UUID orderId
    ) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUserId(
            @PathVariable UUID userId
    ) {
        return orderService.getOrdersByUserId(userId);
    }
}