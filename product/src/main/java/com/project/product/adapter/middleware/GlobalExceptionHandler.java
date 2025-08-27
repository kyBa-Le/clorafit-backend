package com.project.product.adapter.middleware;

import com.project.product.adapter.restApi.dto.error.Error;
import com.project.product.adapter.restApi.dto.error.ErrorDetails;
import com.project.product.adapter.restApi.dto.response.ErrorResponse;
import com.project.product.business.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        List<ErrorDetails> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ErrorDetails(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        Error error = new Error("Validation failed for one or more fields.", "VALIDATION_ERROR", errors);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                error,
                ((ServletWebRequest)request).getRequest().getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), "RESOURCE_NOT_FOUND", null);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND,
                error,
                ((ServletWebRequest)request).getRequest().getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        Error error = new Error(ex.getMessage(), "ILLEGAL_ARGUMENT", null);
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST,
                error,
                ((ServletWebRequest)request).getRequest().getRequestURI()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
