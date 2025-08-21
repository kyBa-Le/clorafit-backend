package com.project.order.domain.exception;

import com.project.order.presentation.dto.error.ErrorDetail;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    public final ErrorDetail errorDetail;

    public ResourceNotFoundException(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
    }
}
