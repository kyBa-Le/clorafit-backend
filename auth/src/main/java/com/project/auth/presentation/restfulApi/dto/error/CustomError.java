package com.project.auth.presentation.restfulApi.dto.error;

import java.util.ArrayList;

public record CustomError(
    String code,
    String message,
    ArrayList<ErrorDetails> details,
    String timestamp
) {}
