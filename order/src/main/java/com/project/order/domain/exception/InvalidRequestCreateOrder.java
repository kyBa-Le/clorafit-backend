package com.project.order.domain.exception;

import com.project.order.adapter.restApi.dto.error.ErrorDetail;

public class InvalidRequestCreateOrder extends RuntimeException {
    public final ErrorDetail errorDetail;

    public InvalidRequestCreateOrder(String message, ErrorDetail errorDetail) {
        super(message);
        this.errorDetail = errorDetail;
    }
}
