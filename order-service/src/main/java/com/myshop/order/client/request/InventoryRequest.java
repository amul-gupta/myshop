package com.myshop.order.client.request;

import lombok.Data;

import java.util.UUID;

@Data
public class InventoryRequest {

    private UUID productId;
    private Integer quantity;
}