package com.project.auth.presentation.restfulApi.dto.base;

public record SuccessResponse<T>(
        String message,
        T data
) {}
