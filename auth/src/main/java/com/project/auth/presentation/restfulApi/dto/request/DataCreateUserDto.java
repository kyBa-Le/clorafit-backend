package com.project.auth.presentation.restfulApi.dto.request;

import com.project.auth.domain.entity.Role;
import com.project.auth.domain.validation.PasswordRule;
import com.project.auth.domain.validation.PhoneRule;
import jakarta.validation.constraints.NotNull;

public record DataCreateUserDto(
        @PhoneRule String phone,
        @PasswordRule String password,
        @NotNull Role role
) {
}
