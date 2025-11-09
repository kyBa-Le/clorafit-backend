package com.project.order.domain.event;

import lombok.Getter;

@Getter
public abstract class BaseEvent {
    private final String eventType;

    protected BaseEvent(String eventType) {
        this.eventType = eventType;
    }

}
