package com.myshop.order.producer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.myshop.common.event.OrderCreatedEvent;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

        public void publish(OrderCreatedEvent event) {
            log.info("Order Created Event Published: {}", event);
             kafkaTemplate.send("order-created", event);
        }
}


