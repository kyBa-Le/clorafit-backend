package com.project.order.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Orders {
    @Id
    private final String id = UUID.randomUUID().toString();
    private double amount;
    private Date date;
    private OrderStatus status;
    private String consumerId;
    private String productId;
    private int quantity;
    private String note;
    private String properties;

    public Orders() {}

    public Orders(double amount, Date date, OrderStatus status, String consumerId,
                  String productId, int quantity, String note, String properties)
    {
        this.amount = amount;
        this.date = date;
        this.status = status;
        this.consumerId = consumerId;
        this.productId = productId;
        this.quantity = quantity;
        this.note = note;
        this.properties = properties;
    }
}
