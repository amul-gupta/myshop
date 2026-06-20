package com.myshop.payment.producer;

import com.myshop.common.event.PaymentCompletedEvent;
import com.myshop.common.event.PaymentFailedEvent;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void publishPaymentCompleted(PaymentCompletedEvent event) {

        kafkaTemplate.send(
                "payment-completed-topic",
                event.getOrderId().toString(),
                event
        );
    }

    public void publishPaymentFailed(PaymentFailedEvent event) {

        kafkaTemplate.send(
                "payment-failed-topic",
                event.getOrderId().toString(),
                event
        );
    }
}