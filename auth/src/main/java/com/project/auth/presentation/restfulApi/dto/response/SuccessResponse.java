package com.project.auth.presentation.restfulApi.dto.response;

public record SuccessResponse<T>(
        String message,
        T data
) {}
