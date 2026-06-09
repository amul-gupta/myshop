package com.myshop.order.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
public class OrderItemResponse {

    private Long id;

    private UUID productId;

    private Integer quantity;

    private BigDecimal subTotal;
}