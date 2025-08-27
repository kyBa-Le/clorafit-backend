package com.project.product.business.service;

import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import com.project.product.business.model.ProductSearchCriteria;
import com.project.product.infrastructure.persistence.repository.CustomProductRepository;
import com.project.product.infrastructure.persistence.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.project.product.business.exception.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomProductRepository customProductRepository;

    public ProductService(ProductRepository productRepository, CustomProductRepository customProductRepository) {
        this.productRepository = productRepository;
        this.customProductRepository = customProductRepository;
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

    public Page<Product> getProductsByCategory(ProductSearchCriteria criteria) {
        return customProductRepository.searchProductsByCategory(criteria);
    }

    @Transactional
    public Product updateProductQuantity(String productId, int updatedQuantity) {
        Product product = getProductById(productId);
        product.setQuantity(updatedQuantity);
        return productRepository.save(product);
    }


}
