package com.project.order.domain.exception;

import com.project.order.presentation.dto.error.ErrorDetail;

public class InvalidValueException extends RuntimeException {
    public final ErrorDetail errorDetail;

    public InvalidValueException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
    }
}
