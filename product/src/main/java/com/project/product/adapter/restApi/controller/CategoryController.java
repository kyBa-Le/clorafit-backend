package com.project.product.adapter.restApi.controller;
import com.project.product.business.service.CategoryService;
import com.project.product.adapter.restApi.dto.response.SuccessResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/v1/categories")
    public ResponseEntity<SuccessResponse<?>> getAllCategories() {
        var categories = categoryService.findAll();
        String message = "Get categories successful!";

        return ResponseEntity.ok(new SuccessResponse<>(message, categories));
    }
}
