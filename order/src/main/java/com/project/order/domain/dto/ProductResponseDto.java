package com.project.order.domain.dto;

public record ProductResponseDto(
        String id,
        String name,
        double price,
        float discount,
        String shopId,
        int quantity,
        String categoryId
) {
}
