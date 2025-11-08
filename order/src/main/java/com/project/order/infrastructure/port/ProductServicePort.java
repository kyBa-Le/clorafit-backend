package com.project.order.infrastructure.port;

import com.project.order.adapter.grpc.dto.ProductResponseDto;

public interface ProductServicePort {
    ProductResponseDto getProductById(String productId);
}
