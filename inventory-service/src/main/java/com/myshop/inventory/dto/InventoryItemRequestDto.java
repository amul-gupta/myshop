package com.myshop.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryItemRequestDto {


    @NotNull
    private UUID productId;

    @NotBlank
    private String sku;

    @NotBlank
    private String productName;

    @NotBlank
    private String warehouseLocation;

    @NotNull
    @Min(0)
    private Integer availableQuantity;

    @NotNull
    @Min(0)
    private Integer reservedQuantity;

    @NotNull
    @Min(0)
    private Integer reorderLevel;

    @NotNull
    private Boolean active;
}