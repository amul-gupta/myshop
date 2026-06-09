package com.myshop.inventory.mapper;

import com.myshop.inventory.dto.InventoryItemRequestDto;
import com.myshop.inventory.dto.InventoryItemResponseDto;
import com.myshop.inventory.entity.InventoryItem;
import org.springframework.stereotype.Component;

@Component
public class InventoryItemMapper {

    public  InventoryItem toEntity(InventoryItemRequestDto dto) {

        if (dto == null) {
            return null;
        }

        return InventoryItem.builder()
                .productId(dto.getProductId())
                .sku(dto.getSku())
                .productName(dto.getProductName())
                .warehouseLocation(dto.getWarehouseLocation())
                .availableQuantity(dto.getAvailableQuantity())
                .reservedQuantity(dto.getReservedQuantity())
                .reorderLevel(dto.getReorderLevel())
                .active(dto.getActive())
                .build();
    }

    public InventoryItemResponseDto toResponseDto(InventoryItem entity) {

        if (entity == null) {
            return null;
        }

        return InventoryItemResponseDto.builder()
                .id(entity.getId())
                .productId(entity.getProductId())
                .sku(entity.getSku())
                .productName(entity.getProductName())
                .warehouseLocation(entity.getWarehouseLocation())
                .availableQuantity(entity.getAvailableQuantity())
                .reservedQuantity(entity.getReservedQuantity())
                .reorderLevel(entity.getReorderLevel())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public void updateEntityFromDto(
            InventoryItemRequestDto dto,
            InventoryItem entity) {

        entity.setProductId(dto.getProductId());
        entity.setSku(dto.getSku());
        entity.setProductName(dto.getProductName());
        entity.setWarehouseLocation(dto.getWarehouseLocation());
        entity.setAvailableQuantity(dto.getAvailableQuantity());
        entity.setReservedQuantity(dto.getReservedQuantity());
        entity.setReorderLevel(dto.getReorderLevel());

        if (dto.getActive() != null) {
            entity.setActive(dto.getActive());
        }
    }
}