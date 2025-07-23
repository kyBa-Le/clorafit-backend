package com.project.product.business.service;

import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import com.project.product.persistence.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.project.product.business.exception.ResourceNotFoundException;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public Product createProduct(String name, String description, double price, float discount, int quantity,
                                 String shopId, List<String> imageLinks, Category category) {
        Product product = new Product(name, description, price, discount, quantity, shopId, imageLinks, category);
        return productRepository.save(product);
    }

    public Product getProductById(String productId) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
        return productRepository.findById(productId).get();
    }

    public List<Product> getAllProductsByCategoryId(String categoryId) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public Page<Product> getTopSoldProductsByCategoryId(String categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "soldCount"));
        Category category = categoryService.getCategoryById(categoryId);
        return productRepository.findProductByCategory(category, pageable);
    }
}
