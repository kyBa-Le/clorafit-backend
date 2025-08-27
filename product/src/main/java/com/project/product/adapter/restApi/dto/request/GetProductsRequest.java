package com.project.product.adapter.restApi.dto.request;

public record GetProductsRequest(
        String name,
        Integer page,
        Integer size,
        String sort,
        String direction
) {
}
