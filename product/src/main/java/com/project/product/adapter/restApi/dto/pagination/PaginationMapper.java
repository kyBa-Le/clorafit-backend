package com.project.product.adapter.restApi.dto.pagination;

import org.springframework.data.domain.Page;

import java.util.List;

public class PaginationMapper {
    public static <T> PaginationResponse<T> map(Page<T> page) {
        List<T> items = page.getContent();
        int pages = page.getPageable().getPageNumber();
        int size = page.getPageable().getPageSize();
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        boolean isFirst = page.isFirst();
        boolean isLast = page.isLast();
        boolean hasNext = page.hasNext();
        boolean hasPrevious = page.hasPrevious();

        PaginationData data = new PaginationData(pages, size, totalItems, totalPages, isFirst, isLast, hasNext, hasPrevious);

        return new PaginationResponse<>(items, data);

    }
}
