package com.project.auth.presentation.restfulApi.dto.request;

import com.project.auth.domain.validation.PasswordRule;
import com.project.auth.domain.validation.PhoneRule;

public record DataCreateUserDto(
        @PhoneRule String phone,
        @PasswordRule String password
) {
}
