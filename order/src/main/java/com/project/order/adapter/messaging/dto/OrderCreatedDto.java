package com.project.order.adapter.messaging.dto;

public record OrderCreatedDto(
        String productId,
        int quantity
) {
}
