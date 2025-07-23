package com.project.product.presentation.dto.pagination;

import java.util.List;

public record PaginationResponse<T>(
        List<T> items,
        PaginationData pagination
) {
}
