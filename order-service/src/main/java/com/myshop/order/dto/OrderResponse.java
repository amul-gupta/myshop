package com.myshop.order.dto;

import com.myshop.order.entity.OrderStatus;
import com.myshop.order.entity.PaymentMethod;
import com.myshop.order.entity.PaymentStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class OrderResponse {

    private UUID id;

    private UUID userId;

    private String billingName;

    private String billingPhoneNumber;

    private String shippingAddress;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private OrderStatus orderStatus;

    private BigDecimal totalAmount;

    private List<OrderItemResponse> orderItems;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private LocalDateTime canceledAt;
}