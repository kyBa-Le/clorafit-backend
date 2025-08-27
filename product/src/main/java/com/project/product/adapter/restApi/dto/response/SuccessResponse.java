package com.project.product.adapter.restApi.dto.response;

public record SuccessResponse<T>(
        String message,
        T data
) {
}
