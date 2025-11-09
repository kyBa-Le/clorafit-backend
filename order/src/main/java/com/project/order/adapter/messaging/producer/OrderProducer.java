package com.project.order.adapter.messaging.producer;

import lombok.Getter;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Getter
@Service
public class OrderProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

}
