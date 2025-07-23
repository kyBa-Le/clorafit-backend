package com.project.product.business.service;

import com.project.product.business.entity.Category;
import com.project.product.business.exception.ResourceNotFoundException;
import com.project.product.persistence.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category getCategoryById(String id) {
        if (categoryRepository.findById(id).isPresent()) {
            return categoryRepository.findById(id).get();
        }
        throw new ResourceNotFoundException("Category not found");
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
