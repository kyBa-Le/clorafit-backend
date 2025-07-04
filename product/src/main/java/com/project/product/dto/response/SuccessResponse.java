package com.project.product.dto.response;

public record SuccessResponse<T>(
        String message,
        T data
) {
}
