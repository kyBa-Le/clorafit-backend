package com.project.product.adapter.restApi.dto.request;

import com.project.product.business.validation.DiscountRule;
import com.project.product.business.validation.ProductNameRule;
import com.project.product.business.validation.ProductPriceRule;
import com.project.product.business.validation.ProductQuantityRule;

import java.util.List;

public record CreateProductDto(
        @ProductNameRule String name,
        String description,
        @ProductPriceRule double price,
        @DiscountRule float discount,
        @ProductQuantityRule int quantity,
        List<String> imageLinks,
        String categoryId
        ) {
}
