package com.myshop.common.event;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderCreatedEvent {

    private UUID orderId;
    private UUID userId;
    private BigDecimal amount;
    private String paymentMethod;

}
