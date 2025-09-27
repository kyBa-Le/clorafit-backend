package com.project.product.adapter.messaging.dto;

public record OrderCreatedDto(
        String productId,
        int quantity
) {
}
