package com.myshop.order.service.impl;

import com.myshop.order.dto.AddCartItemRequest;
import com.myshop.order.dto.UpdateCartItemRequest;
import com.myshop.order.dto.CartItemResponseDTO;
import com.myshop.order.dto.CartResponseDTO;
import com.myshop.order.entity.Cart;
import com.myshop.order.entity.CartItem;
import com.myshop.order.entity.CartStatus;
import com.myshop.order.repository.CartRepository;
import com.myshop.order.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartResponseDTO addItem(UUID userId,
                                   AddCartItemRequest request) {

        Cart cart = cartRepository
                .findByUserIdAndCartStatus(userId, CartStatus.ACTIVE)
                .orElseGet(() -> createCart(userId));

        Optional<CartItem> existingItem = cart.getCartItemList()
                .stream()
                .filter(item ->
                        item.getProductId()
                                .equals(request.getProductId()))
                .findFirst();

        if (existingItem.isPresent()) {

            CartItem item = existingItem.get();

            item.setQuantity(
                    item.getQuantity() + request.getQuantity()
            );

        } else {

            CartItem item = CartItem.builder()
                    .productId(request.getProductId())
                    .quantity(request.getQuantity())
                    .cart(cart)
                    .build();

            cart.getCartItemList().add(item);
        }

        Cart savedCart = cartRepository.save(cart);

        return mapToResponse(savedCart);
    }

    @Override
    public CartResponseDTO updateItem(UUID userId,
                                      Long cartItemId,
                                      UpdateCartItemRequest request) {

        Cart cart = getActiveCart(userId);

        CartItem item = cart.getCartItemList()
                .stream()
                .filter(i -> i.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        item.setQuantity(request.getQuantity());
        Cart savedCart = cartRepository.save(cart);

        return mapToResponse(savedCart);
    }

    @Override
    @Transactional(readOnly = true)
    public CartResponseDTO getCartByUserId(UUID userId) {

        Cart cart = getActiveCart(userId);

        return mapToResponse(cart);
    }

    @Override
    public CartResponseDTO removeItem(UUID userId,
                                      Long cartItemId) {

        Cart cart = getActiveCart(userId);

        CartItem item = cart.getCartItemList()
                .stream()
                .filter(i -> i.getId().equals(cartItemId))
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Cart item not found"));

        cart.getCartItemList().remove(item);

        Cart savedCart = cartRepository.save(cart);

        return mapToResponse(savedCart);
    }

    @Override
    public void clearCart(UUID userId) {

        Cart cart = getActiveCart(userId);

        cart.getCartItemList().clear();

        cartRepository.save(cart);
    }

    private Cart getActiveCart(UUID userId) {

        return cartRepository
                .findByUserIdAndCartStatus(
                        userId,
                        CartStatus.ACTIVE
                )
                .orElseThrow(() ->
                        new RuntimeException("Cart not found"));
    }

    private Cart createCart(UUID userId) {

        Cart cart = Cart.builder()
                .userId(userId)
                .cartStatus(CartStatus.ACTIVE)
                .build();

        return cartRepository.save(cart);
    }

    private CartResponseDTO mapToResponse(Cart cart) {

        List<CartItemResponseDTO> items =
                cart.getCartItemList()
                        .stream()
                        .map(item -> CartItemResponseDTO.builder()
                                .id(item.getId())
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .build())
                        .toList();

        return CartResponseDTO.builder()
                .id(cart.getId())
                .userId(cart.getUserId())
                .cartStatus(cart.getCartStatus())
                .items(items)
                .createdAt(cart.getCreatedAt())
                .updatedAt(cart.getUpdatedAt())
                .checkedOutAt(cart.getCheckedOutAt())
                .build();
    }
}