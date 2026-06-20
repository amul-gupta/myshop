package com.myshop.payment.service.impl;

import com.myshop.payment.entity.Payment;
import com.myshop.payment.entity.PaymentMethod;
import com.myshop.payment.entity.PaymentStatus;
import com.myshop.common.event.PaymentCompletedEvent;
import com.myshop.common.event.PaymentFailedEvent;
import com.myshop.payment.producer.PaymentEventProducer;
import com.myshop.payment.repository.PaymentRepository;
import com.myshop.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.myshop.common.event.OrderCreatedEvent;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentEventProducer paymentEventProducer;

    @Override
    public void processPayment(OrderCreatedEvent event) {

        // Prevent duplicate payment creation
        if (paymentRepository.findByOrderId(event.getOrderId()).isPresent()) {
            return;
        }

        Payment payment = Payment.builder()
                .orderId(event.getOrderId())
                .userId(event.getUserId())
                .amount(event.getAmount())
                .paymentMethod(
                        PaymentMethod.valueOf(event.getPaymentMethod())
                )
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        payment = paymentRepository.save(payment);

        try {

            payment.setPaymentStatus(PaymentStatus.PROCESSING);
            paymentRepository.save(payment);

            // Simulate payment gateway success
            String transactionId = UUID.randomUUID().toString();
            payment.setTransactionId(transactionId);
            payment.setGatewayName("RAZORPAY");
            payment.setPaymentStatus(PaymentStatus.COMPLETED);
            payment.setPaidAt(LocalDateTime.now());

            paymentRepository.save(payment);

            PaymentCompletedEvent completedEvent =
                    PaymentCompletedEvent.builder()
                            .orderId(payment.getOrderId())
                            .paymentId(payment.getId())
                            .transactionId(transactionId)
                            .build();

            paymentEventProducer.publishPaymentCompleted(completedEvent);

        } catch (Exception ex) {

            payment.setPaymentStatus(PaymentStatus.FAILED);
            payment.setFailureReason(ex.getMessage());

            paymentRepository.save(payment);

            PaymentFailedEvent failedEvent =
                    PaymentFailedEvent.builder()
                            .orderId(payment.getOrderId())
                            .reason(ex.getMessage())
                            .build();

            paymentEventProducer.publishPaymentFailed(failedEvent);
        }
    }
}