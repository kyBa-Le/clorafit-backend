package com.project.product.adapter.restApi.dto.pagination;

import java.util.List;

public record PaginationResponse<T>(
        List<T> items,
        PaginationData pagination
) {
}
