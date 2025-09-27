package com.project.product.infrastructure.persistence.repository;

import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Page<Product> findProductByCategory(Category category, Pageable pageable);
}
