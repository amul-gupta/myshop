package com.myshop.order.service;

import com.myshop.order.dto.CartResponseDTO;
import com.myshop.order.dto.AddCartItemRequest;
import com.myshop.order.dto.UpdateCartItemRequest;


import java.util.UUID;

public interface CartService {

    CartResponseDTO addItem(UUID userId, AddCartItemRequest request);

    CartResponseDTO updateItem(UUID userId,
                            Long cartItemId,
                            UpdateCartItemRequest request);

    CartResponseDTO getCartByUserId(UUID userId);

    CartResponseDTO removeItem(UUID userId, Long cartItemId);

    void clearCart(UUID userId);
}