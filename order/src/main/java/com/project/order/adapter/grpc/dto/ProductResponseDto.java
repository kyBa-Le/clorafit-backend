package com.project.order.adapter.grpc.dto;

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
