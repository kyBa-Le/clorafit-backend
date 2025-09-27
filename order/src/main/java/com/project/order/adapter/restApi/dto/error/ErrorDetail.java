package com.project.order.adapter.restApi.dto.error;

public record ErrorDetail (
        String field,
        String message
){
}