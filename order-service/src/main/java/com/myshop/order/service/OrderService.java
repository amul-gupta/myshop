package com.myshop.order.service;

import com.myshop.order.dto.CreateOrderRequest;
import com.myshop.order.dto.OrderResponse;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    OrderResponse cancelOrder(UUID orderId);

    List<OrderResponse> getOrdersByUserId(UUID userId);

    OrderResponse getOrderById(UUID orderId);
}