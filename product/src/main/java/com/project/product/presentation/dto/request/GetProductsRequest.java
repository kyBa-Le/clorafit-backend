package com.project.product.presentation.dto.request;

public record GetProductsRequest(
        String name,
        Integer page,
        Integer size,
        String sort,
        String direction
) {
}
