package com.project.product.business.model;

import com.project.product.business.entity.Category;
import com.project.product.presentation.constants.ProductSortFieldMapper;
import com.project.product.presentation.dto.request.GetProductsRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;
import java.util.Objects;

@Getter
@Setter
public class ProductSearchCriteria {
    private Category category;
    private String name;
    private int page;
    private int size;
    private String sort;
    private Sort.Direction direction;


    public static ProductSearchCriteria of(GetProductsRequest request) {
        ProductSearchCriteria criteria = new ProductSearchCriteria();

        criteria.setPage(Objects.requireNonNullElse(request.page(), 0));
        criteria.setSize(Objects.requireNonNullElse(request.size(), 50));
        criteria.setSort(Objects.requireNonNullElse(request.sort(), ""));
        criteria.setName(request.name());

        Sort.Direction direction = request.direction() != null
                ? Sort.Direction.fromString(request.direction()) : Sort.Direction.DESC;
        criteria.setDirection(direction);

        return criteria;
    }

    public void setSort(String sort) {
        String sortField = ProductSortFieldMapper.SORT_FIELDS.get(sort);
        if (sortField == null) {
            throw new IllegalArgumentException("Invalid sort field: " + sort + ", possible values: " + ProductSortFieldMapper.getSortFields());
        }
        this.sort = sortField;
    }
}
