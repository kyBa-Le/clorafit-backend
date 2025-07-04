package com.project.product.dto.request;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record CreateProductDto(
        @NotBlank(message = "Product name cannot be empty")
        String name,
        String description,
        @Positive(message = "Price must be positive")
        double price,
        @Min(value = 0, message = "Discount must be positive")
        @DecimalMax(value = "0.999999", message = "Discount must be smaller than 1")
        float discount,
        @Positive(message = "Quantity must be positive")
        int quantity,
        List<String> imageLinks,
        String categoryId
        ) {
}
