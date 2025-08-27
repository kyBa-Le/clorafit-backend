package com.project.product.adapter.restApi.dto.pagination;

public record PaginationData(
        int page,
        int size,
        long totalItems,
        int totalPage,
        boolean isFirst,
        boolean isLast,
        boolean hasNext,
        boolean hasPrevious
) {
}
