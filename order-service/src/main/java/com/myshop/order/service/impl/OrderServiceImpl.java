package com.myshop.order.service.impl;

import com.myshop.order.client.InventoryClient;
import com.myshop.order.client.ProductClient;
import com.myshop.order.client.request.InventoryRequest;
import com.myshop.order.client.response.ProductResponse;
import com.myshop.order.dto.CreateOrderRequest;
import com.myshop.order.dto.OrderItemResponse;
import com.myshop.order.dto.OrderResponse;
import com.myshop.order.entity.*;
import com.myshop.order.repository.CartRepository;
import com.myshop.order.repository.OrderRepository;
import com.myshop.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    private final ProductClient productClient;
    private final InventoryClient inventoryClient;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        Cart cart = cartRepository.findByUserId(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        if (cart.getCartItemList().isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Validate stock first
        for (CartItem cartItem : cart.getCartItemList()) {

            InventoryRequest inventoryRequest = new InventoryRequest();
            inventoryRequest.setProductId(cartItem.getProductId());
            inventoryRequest.setQuantity(cartItem.getQuantity());

            Boolean available = inventoryClient.checkStock(inventoryRequest);

            if (!Boolean.TRUE.equals(available)) {
                throw new RuntimeException(
                        "Insufficient stock for product: "
                                + cartItem.getProductId()
                );
            }
        }

        Order order = new Order();

        order.setUserId(request.getUserId());
        order.setBillingName(request.getBillingName());
        order.setBillingPhoneNumber(request.getBillingPhoneNumber());
        order.setShippingAddress(request.getShippingAddress());

        order.setPaymentMethod(request.getPaymentMethod());
        order.setPaymentStatus(PaymentStatus.PENDING);
        order.setOrderStatus(OrderStatus.CONFIRMED);

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getCartItemList()) {

            ProductResponse product =
                    productClient.getProductById(cartItem.getProductId());

            BigDecimal subTotal =
                    BigDecimal.valueOf(product.getPrice() * cartItem.getQuantity());

            OrderItem orderItem = OrderItem.builder()
                    .productId(cartItem.getProductId())
                    .quantity(cartItem.getQuantity())
                    .subTotal(subTotal)
                    .order(order)
                    .build();

            order.getOrderItemList().add(orderItem);

            totalAmount = totalAmount.add(subTotal);
        }

        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        // Deduct inventory
        for (CartItem cartItem : cart.getCartItemList()) {

            InventoryRequest inventoryRequest = new InventoryRequest();
            inventoryRequest.setProductId(cartItem.getProductId());
            inventoryRequest.setQuantity(cartItem.getQuantity());

            inventoryClient.updateStock(inventoryRequest);
        }

        // Clear cart
        cart.getCartItemList().clear();
        cart.setCartStatus(CartStatus.CHECKED_OUT);
        cart.setCheckedOutAt(LocalDateTime.now());

        cartRepository.save(cart);

        return mapToResponse(savedOrder);
    }

    @Override
    public OrderResponse cancelOrder(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setOrderStatus(OrderStatus.CANCELLED);
        order.setCanceledAt(LocalDateTime.now());

        Order updatedOrder = orderRepository.save(order);

        return mapToResponse(updatedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponse> getOrdersByUserId(UUID userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(UUID orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        return mapToResponse(order);
    }

    private OrderResponse mapToResponse(Order order) {

        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .billingName(order.getBillingName())
                .billingPhoneNumber(order.getBillingPhoneNumber())
                .shippingAddress(order.getShippingAddress())
                .paymentMethod(order.getPaymentMethod())
                .paymentStatus(order.getPaymentStatus())
                .orderStatus(order.getOrderStatus())
                .totalAmount(order.getTotalAmount())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .canceledAt(order.getCanceledAt())
                .orderItems(
                        order.getOrderItemList()
                                .stream()
                                .map(item -> OrderItemResponse.builder()
                                        .id(item.getId())
                                        .productId(item.getProductId())
                                        .quantity(item.getQuantity())
                                        .subTotal(item.getSubTotal())
                                        .build())
                                .toList()
                )
                .build();
    }
}