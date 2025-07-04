package com.project.product.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.product.dto.error.Error;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ErrorResponse(
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime timestamp,
        int status,
        Error error,
        String path
) {
    public ErrorResponse(LocalDateTime timestamp, HttpStatus status, Error error, String path) {
        this(timestamp, status.value(), error, path);
    }
}