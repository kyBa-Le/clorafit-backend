package com.project.order.adapter.messaging.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.order.adapter.messaging.dto.OrderCreatedDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final Logger log = LoggerFactory.getLogger(OrderProducer.class);

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void orderCreatedEvent(String productId, int quantity) throws JsonProcessingException {
        var dto = new OrderCreatedDto(productId, quantity);
        var message = objectMapper.writeValueAsString(dto);
        kafkaTemplate.send("order-created", message);
        log.info("Request decrease product quantity sent: {}", message);
    }
}
