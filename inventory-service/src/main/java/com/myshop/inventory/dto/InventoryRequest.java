package com.myshop.inventory.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class InventoryRequest {

    private UUID productId;

    private Integer quantity;
}
