package com.myshop.inventory.service.impl;

import com.myshop.inventory.dto.InventoryItemRequestDto;
import com.myshop.inventory.dto.InventoryItemResponseDto;
import com.myshop.inventory.dto.InventoryRequest;
import com.myshop.inventory.entity.InventoryItem;
import com.myshop.inventory.exception.ResourceNotFoundException;
import com.myshop.inventory.mapper.InventoryItemMapper;
import com.myshop.inventory.repository.InventoryItemRepository;
import com.myshop.inventory.service.InventoryItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InventoryItemServiceImpl implements InventoryItemService {

    private final InventoryItemRepository repository;
    private final InventoryItemMapper mapper;

    @Override
    public InventoryItemResponseDto createInventoryItem(
            InventoryItemRequestDto requestDto) {

        if (repository.existsBySku(requestDto.getSku())) {
            throw new IllegalArgumentException(
                    "Inventory item already exists with SKU: " + requestDto.getSku());
        }

        if (repository.existsByProductId(requestDto.getProductId())) {
            throw new IllegalArgumentException(
                    "Inventory item already exists with Product ID: " + requestDto.getProductId());
        }

        InventoryItem inventoryItem = mapper.toEntity(requestDto);

        InventoryItem savedItem = repository.save(inventoryItem);

        return mapper.toResponseDto(savedItem);
    }

    @Override
    public InventoryItemResponseDto getInventoryItemById(Long id) {

        InventoryItem inventoryItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory item not found with id: " + id));

        return mapper.toResponseDto(inventoryItem);
    }

    @Override
    public InventoryItemResponseDto getInventoryItemByProductId(UUID productId) {

        InventoryItem inventoryItem = repository.findByProductId(productId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory item not found with productId: " + productId));

        return mapper.toResponseDto(inventoryItem);
    }

    @Override
    public InventoryItemResponseDto getInventoryItemBySku(String sku) {

        InventoryItem inventoryItem = repository.findBySku(sku)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory item not found with sku: " + sku));

        return mapper.toResponseDto(inventoryItem);
    }

    @Override
    public List<InventoryItemResponseDto> getAllInventoryItems() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponseDto)
                .toList();
    }

    @Override
    public InventoryItemResponseDto updateInventoryItem(
            Long id,
            InventoryItemRequestDto requestDto) {

        InventoryItem existingItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory item not found with id: " + id));

        // SKU uniqueness check
        repository.findBySku(requestDto.getSku())
                .ifPresent(item -> {
                    if (!item.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "SKU already exists: " + requestDto.getSku());
                    }
                });

        // ProductId uniqueness check
        repository.findByProductId(requestDto.getProductId())
                .ifPresent(item -> {
                    if (!item.getId().equals(id)) {
                        throw new IllegalArgumentException(
                                "Product ID already exists: " + requestDto.getProductId());
                    }
                });

        mapper.updateEntityFromDto(requestDto, existingItem);

        InventoryItem updatedItem = repository.save(existingItem);

        return mapper.toResponseDto(updatedItem);
    }

    @Override
    public void deleteInventoryItem(Long id) {

        InventoryItem inventoryItem = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Inventory item not found with id: " + id));

        repository.delete(inventoryItem);
    }

    @Override
    public boolean checkStock(InventoryRequest request) {

        InventoryItem inventoryItem = repository
                .findByProductId(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        return inventoryItem.getAvailableQuantity() >= request.getQuantity();
    }

    @Override
    @Transactional
    public void updateStock(InventoryRequest request) {

        InventoryItem inventoryItem = repository
                .findByProductId(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Inventory item not found"));

        if (inventoryItem.getAvailableQuantity() < request.getQuantity()) {
            throw new RuntimeException("Insufficient stock");
        }

        inventoryItem.setAvailableQuantity(
                inventoryItem.getAvailableQuantity() - request.getQuantity()
        );

        repository.save(inventoryItem);
    }

}