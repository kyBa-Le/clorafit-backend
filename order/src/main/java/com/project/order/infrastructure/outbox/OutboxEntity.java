package com.project.order.infrastructure.outbox;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OutboxEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String event;
    private String payload;
    private boolean isPublished;

    public OutboxEntity() {}

    public OutboxEntity(String event, String payload, boolean isPublished) {
        this.event = event;
        this.payload = payload;
        this.isPublished = isPublished;
    }
}
