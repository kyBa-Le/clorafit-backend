package com.project.auth.presentation.restfulApi.dto.response;

import java.util.Date;

public record CreateUserResponseDto(
        String username,
        String fullName,
        String email,
        Date dateOfBirth,
        String phone
) {
}
