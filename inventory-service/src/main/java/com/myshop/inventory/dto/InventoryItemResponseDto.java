package com.myshop.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemResponseDto {

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