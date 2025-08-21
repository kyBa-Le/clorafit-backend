package com.project.order.infrastructure.port;

import com.project.order.domain.dto.ProductResponseDto;

public interface ProductServicePort {
    ProductResponseDto getProductById(String productId);
}
