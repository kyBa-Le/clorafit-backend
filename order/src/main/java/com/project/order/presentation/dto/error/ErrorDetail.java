package com.project.order.presentation.dto.error;

public record ErrorDetail (
        String field,
        String message
){
}