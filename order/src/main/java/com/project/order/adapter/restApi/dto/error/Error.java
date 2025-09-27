package com.project.order.adapter.restApi.dto.error;

import java.util.List;

public record Error(String message, String code, List<ErrorDetail> details) {
}