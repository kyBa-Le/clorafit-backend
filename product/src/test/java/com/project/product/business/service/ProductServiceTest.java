package com.project.product.business.service;

import com.project.product.business.entity.Category;
import com.project.product.business.entity.Product;
import com.project.product.business.exception.ResourceNotFoundException;
import com.project.product.infrastructure.persistence.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Test
    void createProduct_allFieldsSet_success() {
        var name = "product test";
        var description = "product description";
        var price = 100.0;
        var discount = 0.3f;
        var quantity = 1;
        var shopId = "testId";
        var imageLinks = List.of("testImageLink");
        var category = mock(Category.class);

        when(productRepository.save(any(Product.class))).thenAnswer(invocation -> invocation.getArgument(0));
        var result = productService.createProduct(name, description, price, discount, quantity, shopId, imageLinks, category);

        assertNotNull(result);
        assertEquals(name, result.getName());
        assertEquals(description, result.getDescription());
        assertEquals(price, result.getPrice());
        assertEquals(discount, result.getDiscount());
        assertEquals(quantity, result.getQuantity());
        assertEquals(shopId, result.getShopId());
        assertEquals(imageLinks, result.getImageLinks());
        assertEquals(category, result.getCategory());

        verify(productRepository, times(1)).save(any(Product.class));

    }

    @Test
    void getProductById_invalidId_throwsException() {
        var invalidId = "invalidId";
        when(productRepository.findById(invalidId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> productService.getProductById(invalidId));

        verify(productRepository, times(1)).findById(invalidId);
    }

    @Test
    void getProductById_validId_success() {
        var product = new Product();
        var generatedId = product.getId();

        when(productRepository.findById(generatedId)).thenReturn(Optional.of(product));

        var result = productService.getProductById(generatedId);
        assertNotNull(result);
        assertEquals(generatedId, result.getId());

        verify(productRepository, times(2)).findById(generatedId);
    }

    @Test
    void getAllProductsByCategoryId_returnNotNull() {
        var product = new Product();
        var category = new Category("sampleId", "name", "icon");
        product.setCategory(category);
        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = productService.getAllProductsByCategoryId(category.getId());
        assertNotNull(result);

        verify(productRepository, times(1)).findAll();
    }

    @Test
    void getAllProductsByCategoryId_returnNull() {
        var product = new Product();
        var category = new Category("sampleId", "name", "icon");
        product.setCategory(category);

        when(productRepository.findAll()).thenReturn(List.of(product));

        var result = productService.getAllProductsByCategoryId("invalid_category_id");
        assertEquals( List.of(), result);

        verify(productRepository, times(1)).findAll();
    }
}