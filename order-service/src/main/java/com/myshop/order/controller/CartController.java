package com.myshop.order.controller;

import com.myshop.order.dto.AddCartItemRequest;
import com.myshop.order.dto.UpdateCartItemRequest;
import com.myshop.order.dto.CartResponseDTO;
import com.myshop.order.service.CartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{userId}/items")
    public ResponseEntity<CartResponseDTO> addItem(
            @PathVariable UUID userId,
            @Valid @RequestBody AddCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.addItem(userId, request)
        );
    }

    @PutMapping("/{userId}/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> updateItem(
            @PathVariable UUID userId,
            @PathVariable Long cartItemId,
            @Valid @RequestBody UpdateCartItemRequest request) {

        return ResponseEntity.ok(
                cartService.updateItem(userId, cartItemId, request)
        );
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(
            @PathVariable UUID userId) {

        return ResponseEntity.ok(
                cartService.getCartByUserId(userId)
        );
    }

    @DeleteMapping("/{userId}/items/{cartItemId}")
    public ResponseEntity<CartResponseDTO> removeItem(
            @PathVariable UUID userId,
            @PathVariable Long cartItemId) {

        return ResponseEntity.ok(
                cartService.removeItem(userId, cartItemId)
        );
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> clearCart(
            @PathVariable UUID userId) {

        cartService.clearCart(userId);

        return ResponseEntity.noContent().build();
    }
}