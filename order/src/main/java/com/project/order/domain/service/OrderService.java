package com.project.order.domain.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.order.adapter.grpc.dto.ProductResponseDto;
import com.project.order.domain.entity.OrderStatus;
import com.project.order.domain.entity.Orders;
import com.project.order.domain.event.OrderCreatedEvent;
import com.project.order.domain.validation.OrderValidator;
import com.project.order.infrastructure.outbox.OutboxEventMapper;
import com.project.order.infrastructure.outbox.OutboxRepository;
import com.project.order.infrastructure.persistence.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OutboxEventMapper outboxEventMapper;
    private final OutboxRepository outboxRepository;

    public OrderService(OrderRepository orderRepository, OrderValidator orderValidator,
                        OutboxEventMapper outboxEventMapper, OutboxRepository outboxRepository) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.outboxEventMapper = outboxEventMapper;
        this.outboxRepository = outboxRepository;
    }

    @Transactional
    public Orders createOrder(OrderStatus status, String consumerId, int quantity,
            String note, String properties, ProductResponseDto product
    ) throws JsonProcessingException {
        this.orderValidator.validateRequestCreateOrder(quantity, product);

        double amount = (quantity * product.price());
        var order = new Orders(amount, new Date(), status, consumerId, product.id(), quantity, note, properties);
        this.orderRepository.save(order);

        var orderCreatedEvent = new OrderCreatedEvent(order.getId(), product.id(), quantity);
        this.outboxRepository.save(outboxEventMapper.fromEvent(orderCreatedEvent));

        return order;
    }

    public List<Orders> getOrdersByUserId(String userId) {
        return this.orderRepository.findByConsumerId(userId);
    }
}
