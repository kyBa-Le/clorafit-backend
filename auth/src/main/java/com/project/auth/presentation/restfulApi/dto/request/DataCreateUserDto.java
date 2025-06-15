package com.project.auth.presentation.restfulApi.dto.request;

public record DataCreateUserDto(
        String phone,
        String password
) {
}
