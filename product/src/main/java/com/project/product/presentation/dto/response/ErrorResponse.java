package com.project.product.presentation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.product.presentation.dto.error.Error;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime timestamp,
        int status,
        Error error,
        String path
) {
    public ErrorResponse(LocalDateTime timestamp, HttpStatus status, Error error, String path) {
        this(timestamp, status.value(), error, path);
    }
}