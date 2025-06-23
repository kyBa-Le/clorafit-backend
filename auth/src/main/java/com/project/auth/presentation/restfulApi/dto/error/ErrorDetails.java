package com.project.auth.presentation.restfulApi.dto.error;

public record ErrorDetails(
        String field,
        String message
) {}
