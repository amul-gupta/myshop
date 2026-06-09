package com.myshop.order.client.response;

import java.time.LocalDateTime;
import java.util.UUID;

public class InventoryResponse {


    private Long id;

    private UUID productId;

    private String sku;

    private String productName;

    private String warehouseLocation;

    private Integer availableQuantity;

    private Integer reservedQuantity;

    private Integer reorderLevel;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
