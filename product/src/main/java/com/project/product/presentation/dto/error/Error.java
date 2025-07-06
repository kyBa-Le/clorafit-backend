package com.project.product.presentation.dto.error;

import java.util.List;

public record Error(String message, String code, List<ErrorDetails> details) {
}
