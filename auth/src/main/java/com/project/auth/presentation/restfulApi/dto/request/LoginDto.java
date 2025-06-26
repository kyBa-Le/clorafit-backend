package com.project.auth.presentation.restfulApi.dto.request;

public record LoginDto(
        String phone,
        String password
) {}
