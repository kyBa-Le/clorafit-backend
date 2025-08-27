package com.project.order.adapter.restApi.dto.response;

public record SuccessResponse<T>(
        String message,
        T data
) {
}
