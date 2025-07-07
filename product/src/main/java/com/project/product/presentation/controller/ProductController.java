package com.project.product.presentation.controller;

import com.project.product.presentation.dto.request.CreateProductDto;
import com.project.product.presentation.dto.response.SuccessResponse;
import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import com.project.product.business.service.CategoryService;
import com.project.product.business.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductController(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @PostMapping("/api/v1/products")
    public ResponseEntity<?> addProduct(@Valid @RequestBody CreateProductDto createProductDto) {
        Category category = categoryService.getCategoryById(createProductDto.categoryId());

        var name = createProductDto.name();
        var description = createProductDto.description();
        var price = createProductDto.price();
        var discount = createProductDto.discount();
        var quantity = createProductDto.quantity();
        var imageLinks = createProductDto.imageLinks();
        var shopId = "mock shop Id"; // TODO: connect the user service and get the shop ID later

        Product product = productService.createProduct(name, description, price, discount, quantity, shopId, imageLinks, category);
        return ResponseEntity.ok().body(new SuccessResponse<>("Create product successfully", product));
    }

    @GetMapping("/api/v1/products/{id}")
    public ResponseEntity<?> getProductById(@PathVariable("id") String productId) {
        var product = productService.getProductById(productId);
        return ResponseEntity.ok().body(new SuccessResponse<>("Get product successfully", product));
    }

    @GetMapping("/api/v1/products")
    public ResponseEntity<?> getAllProductsByCategory(@RequestParam("category-id") String categoryId) {
        List<Product> products = productService.getAllProductsByCategoryId(categoryId);
        return ResponseEntity.ok().body(new SuccessResponse<>("Get all products successfully", products));
    }
}
