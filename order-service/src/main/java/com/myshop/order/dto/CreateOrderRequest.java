package com.myshop.order.dto;

import com.myshop.order.entity.PaymentMethod;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateOrderRequest {

    private UUID userId;

    private String billingName;

    private String billingPhoneNumber;

    private String shippingAddress;

    private PaymentMethod paymentMethod;
}