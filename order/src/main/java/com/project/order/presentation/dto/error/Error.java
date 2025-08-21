package com.project.order.presentation.dto.error;

import java.util.List;

public record Error(String message, String code, List<ErrorDetail> details) {
}