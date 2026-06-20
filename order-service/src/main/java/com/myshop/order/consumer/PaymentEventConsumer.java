package com.myshop.order.consumer;

import com.myshop.common.event.PaymentCompletedEvent;
import com.myshop.common.event.PaymentFailedEvent;
import com.myshop.order.entity.Order;
import com.myshop.order.entity.OrderStatus;
import com.myshop.order.entity.PaymentStatus;
import com.myshop.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PaymentEventConsumer {

    private final OrderRepository orderRepository;

    @KafkaListener(
            topics = "payment-completed-topic",
            groupId = "order-group"
    )
    public void handlePaymentCompleted(PaymentCompletedEvent event) {

        log.info("Received PaymentCompletedEvent: {}", event);

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setPaymentStatus(PaymentStatus.COMPLETED);
        order.setOrderStatus(OrderStatus.PROCESSING);
        log.info("Received PaymentCompletedEvent: {}", order.getPaymentStatus()+ " " +order.getOrderStatus());

        orderRepository.save(order);
    }

    @KafkaListener(
            topics = "payment-failed-topic",
            groupId = "order-group"
    )
    public void handlePaymentFailed(PaymentFailedEvent event) {

        log.info("Received PaymentFailedEvent: {}", event);

        Order order = orderRepository.findById(event.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));


        order.setPaymentStatus(PaymentStatus.FAILED);
        order.setOrderStatus(OrderStatus.CANCELLED);


        orderRepository.save(order);
    }
}