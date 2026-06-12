package com.myshop.common.event;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
public class OrderCreatedEvent {

    private String eventId;
    private Long orderId;
    private Long userId;
    private BigDecimal totalAmount;
    private String status;
    private Instant createdAt;
}