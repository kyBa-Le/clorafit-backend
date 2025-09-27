package com.project.product.adapter.restApi.dto.error;

import java.util.List;

public record Error(String message, String code, List<ErrorDetails> details) {
}
