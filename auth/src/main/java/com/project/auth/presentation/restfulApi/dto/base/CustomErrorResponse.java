package com.project.auth.presentation.restfulApi.dto.base;

import com.project.auth.presentation.restfulApi.dto.error.CustomError;

public record CustomErrorResponse(
        CustomError error
) {}
