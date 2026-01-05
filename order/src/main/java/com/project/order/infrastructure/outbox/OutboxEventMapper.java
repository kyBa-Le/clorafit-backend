package com.project.order.infrastructure.outbox;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.order.domain.event.BaseEvent;
import org.springframework.stereotype.Component;

@Component
public class OutboxEventMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public OutboxEntity fromEvent(BaseEvent event) throws JsonProcessingException {
        String payload = objectMapper.writeValueAsString(event);
        return new OutboxEntity(event.getEventType(), payload, false);
    }
}
