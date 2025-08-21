package com.project.order.presentation.dto.response;

public record SuccessResponse<T>(
        String message,
        T data
) {
}
