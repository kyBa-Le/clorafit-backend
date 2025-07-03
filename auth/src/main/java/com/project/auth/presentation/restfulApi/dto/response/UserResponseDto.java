package com.project.auth.presentation.restfulApi.dto.response;

import com.project.auth.domain.enums.Role;

import java.util.Date;

public record UserResponseDto(
        String ID,
        String username,
        String fullName,
        String email,
        Date dateOfBirth,
        String phone,
        Role role
) {
}
