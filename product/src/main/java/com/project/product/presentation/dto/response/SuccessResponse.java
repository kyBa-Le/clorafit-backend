package com.project.product.presentation.dto.response;

public record SuccessResponse<T>(
        String message,
        T data
) {
}
