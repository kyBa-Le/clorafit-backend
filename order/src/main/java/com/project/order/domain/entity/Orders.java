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
    private String consumer_id;
    private String product_id;
    private int quantity;
    private String note;
    private String properties;
}
