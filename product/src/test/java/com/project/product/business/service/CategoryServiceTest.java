package com.project.product.business.service;

import com.project.product.business.entity.Category;
import com.project.product.business.exception.ResourceNotFoundException;
import com.project.product.infrastructure.persistence.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    @Test
    void getCategoryById_validId_returnCategory() {
        var validId = "validId";

        when(categoryRepository.findById(validId)).thenReturn(Optional.of(new Category()));

        var result = categoryService.getCategoryById(validId);
        assertNotNull(result);

        verify(categoryRepository, times(2)).findById(validId);
    }

    @Test
    void getCategoryById_invalidId_throwError() {
        String invalidId = "invalidId";
        when(categoryRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> categoryService.getCategoryById(invalidId));
        verify(categoryRepository, times(1)).findById(invalidId);
    }
}