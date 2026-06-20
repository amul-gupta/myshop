package com.myshop.payment.service;

import com.myshop.common.event.OrderCreatedEvent;

public interface PaymentService {

    void processPayment(OrderCreatedEvent event);
}