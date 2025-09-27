package com.project.order.adapter.restApi.dto.request;

import com.project.order.domain.entity.OrderStatus;

public record CreateOrderDto(
        OrderStatus status,
        String productId,
        int quantity,
        String note,
        String properties
) {
}
