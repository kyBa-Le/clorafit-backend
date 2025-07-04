package com.project.product.business.service;

import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import com.project.product.persistence.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.project.product.business.exception.ResourceNotFoundException;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(String name, String description, double price, float discount, int quantity,
                                 String shopId, List<String> imageLinks, Category categoryId) {
        Product product = new Product(name, description, price, discount, quantity, shopId, imageLinks, categoryId);
        return productRepository.save(product);
    }

    public Product getProductById(String productId) {
        if (productRepository.findById(productId).isEmpty()) {
            throw new ResourceNotFoundException("Product not found");
        }
        return productRepository.findById(productId).get();
    }

    public List<Product> getAllProductsByCategory(String categoryId) {
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getCategory().getId().equals(categoryId))
                .collect(Collectors.toList());
    }
}
