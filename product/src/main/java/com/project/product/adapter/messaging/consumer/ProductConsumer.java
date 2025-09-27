package com.project.product.adapter.messaging.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.product.adapter.messaging.dto.OrderCreatedDto;
import com.project.product.business.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ProductConsumer {

    private final ProductService productService;
    private final ObjectMapper objectMapper;
    private final Logger logger = LoggerFactory.getLogger(ProductConsumer.class);

    public ProductConsumer(ProductService productService, ObjectMapper objectMapper) {
        this.productService = productService;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "order-created", groupId = "1")
    public void orderCreatedListener(String message) throws JsonProcessingException {
        OrderCreatedDto dto = objectMapper.readValue(message, OrderCreatedDto.class);
        var product = productService.getProductById(dto.productId());
        var updatedQuantity = product.getQuantity() - dto.quantity();
        productService.updateProductQuantity(dto.productId(), updatedQuantity);
        logger.info("Product updated: {}", dto.productId());
    }
}
