package com.project.product.controller;

import com.project.product.dto.request.CreateProductDto;
import com.project.product.dto.response.SuccessResponse;
import com.project.product.entity.Category;
import com.project.product.entity.Product;
import com.project.product.service.CategoryService;
import com.project.product.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/v1/products")
    public ResponseEntity<?> addProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        Category category = categoryService.getCategoryById(createProductDto.categoryId());

        var name = createProductDto.name();
        var description = createProductDto.description();
        var price = createProductDto.price();
        var discount = createProductDto.discount();
        var quantity = createProductDto.quantity();
        var imageLinks = createProductDto.imageLinks();
        var shopId = "mock shop Id";

        Product product = productService.createProduct(name, description, price, discount, quantity, shopId, imageLinks, category);
        return ResponseEntity.ok().body(new SuccessResponse<>("Create product successfully", product));
    }

    @GetMapping("/v1/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId) {
        var product = productService.getProductById(productId);
        return ResponseEntity.ok().body(new SuccessResponse<>("Get product successfully", product));
    }
}
