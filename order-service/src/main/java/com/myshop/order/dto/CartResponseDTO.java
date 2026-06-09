package com.myshop.order.dto;


import com.myshop.order.entity.CartStatus;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class CartResponseDTO {

    private Long id;

    private UUID userId;
    private CartStatus cartStatus;

    private List<CartItemResponseDTO> items;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime checkedOutAt;

}
