package com.project.order.presentation.dto.request;

import com.project.order.domain.entity.OrderStatus;

public record CreateOrderDto(
        OrderStatus status,
        String consumerId,
        String productId,
        int quantity,
        String note,
        String properties
) {
}
