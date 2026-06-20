package com.myshop.inventory.controller;

import com.myshop.inventory.dto.InventoryItemRequestDto;
import com.myshop.inventory.dto.InventoryItemResponseDto;
import com.myshop.inventory.dto.InventoryRequest;
import com.myshop.inventory.service.InventoryItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/inventories")
@RequiredArgsConstructor
public class InventoryItemController {

    private final InventoryItemService inventoryItemService;

    @PostMapping
    public ResponseEntity<InventoryItemResponseDto> createInventoryItem(
            @Valid @RequestBody InventoryItemRequestDto requestDto) {

        InventoryItemResponseDto response =
                inventoryItemService.createInventoryItem(requestDto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventoryItemResponseDto> getInventoryItemById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                inventoryItemService.getInventoryItemById(id));
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<InventoryItemResponseDto> getInventoryItemByProductId(
            @PathVariable UUID productId) {

        return ResponseEntity.ok(
                inventoryItemService.getInventoryItemByProductId(productId));
    }

    @GetMapping("/sku/{sku}")
    public ResponseEntity<InventoryItemResponseDto> getInventoryItemBySku(
            @PathVariable String sku) {

        return ResponseEntity.ok(
                inventoryItemService.getInventoryItemBySku(sku));
    }

    @GetMapping
    public ResponseEntity<List<InventoryItemResponseDto>> getAllInventoryItems() {

        return ResponseEntity.ok(
                inventoryItemService.getAllInventoryItems());
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventoryItemResponseDto> updateInventoryItem(
            @PathVariable Long id,
            @Valid @RequestBody InventoryItemRequestDto requestDto) {

        return ResponseEntity.ok(
                inventoryItemService.updateInventoryItem(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInventoryItem(
            @PathVariable Long id) {

        inventoryItemService.deleteInventoryItem(id);

        return ResponseEntity.ok(
                "Inventory item deleted successfully");
    }


    @PostMapping("/check")
    public Boolean checkStock(@RequestBody InventoryRequest request) {
        return inventoryItemService.checkStock(request);
    }

    @PostMapping("/update")
    public void updateStock(@RequestBody InventoryRequest request) {
        inventoryItemService.updateStock(request);
    }
}