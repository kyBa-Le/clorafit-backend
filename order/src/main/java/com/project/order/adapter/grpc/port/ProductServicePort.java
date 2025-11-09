package com.project.order.adapter.grpc.port;

import com.project.order.adapter.grpc.dto.ProductResponseDto;

public interface ProductServicePort {
    ProductResponseDto getProductById(String productId);
}
