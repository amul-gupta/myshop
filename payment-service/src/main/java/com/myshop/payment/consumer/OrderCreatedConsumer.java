package com.myshop.payment.consumer;

import com.myshop.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import com.myshop.common.event.OrderCreatedEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderCreatedConsumer {

    private final PaymentService paymentService;

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(OrderCreatedEvent event) {

        log.info("Received Order Event: {}", event);

        System.out.println("================================");
        System.out.println("ORDER RECEIVED");
        paymentService.processPayment(event);
        System.out.println("================================");
    }
}