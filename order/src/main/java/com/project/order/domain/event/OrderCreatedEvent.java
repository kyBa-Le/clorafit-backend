package com.project.order.domain.event;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderCreatedEvent extends BaseEvent {
    private String orderId;
    private String productId;
    private int quantity;

    public OrderCreatedEvent(String orderId, String productId, int quantity) {
        super("order-created");
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }
}
