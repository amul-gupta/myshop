package com.myshop.order.entity;

public enum OrderStatus {

    PENDING,      // Order created
    CONFIRMED,    // Payment successful
    PROCESSING,   // Being prepared
    SHIPPED,      // Sent to customer
    DELIVERED,    // Successfully delivered
    CANCELLED,    // Cancelled before delivery
    REFUNDED
}