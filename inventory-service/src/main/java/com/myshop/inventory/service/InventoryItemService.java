package com.myshop.inventory.service;

import com.myshop.inventory.dto.InventoryItemRequestDto;
import com.myshop.inventory.dto.InventoryItemResponseDto;
import com.myshop.inventory.dto.InventoryRequest;

import java.util.List;
import java.util.UUID;

public interface InventoryItemService {

    InventoryItemResponseDto createInventoryItem(
            InventoryItemRequestDto requestDto);

    InventoryItemResponseDto getInventoryItemById(
            Long id);

    InventoryItemResponseDto getInventoryItemByProductId(
            UUID productId);

    InventoryItemResponseDto getInventoryItemBySku(
            String sku);

    List<InventoryItemResponseDto> getAllInventoryItems();

    InventoryItemResponseDto updateInventoryItem(
            Long id,
            InventoryItemRequestDto requestDto);

    void deleteInventoryItem(
            Long id);


    boolean checkStock(InventoryRequest request);

    void updateStock(InventoryRequest request);
}