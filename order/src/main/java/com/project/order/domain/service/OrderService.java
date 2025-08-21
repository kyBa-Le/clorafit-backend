package com.project.order.domain.service;

import com.project.order.domain.dto.ProductResponseDto;
import com.project.order.domain.entity.OrderStatus;
import com.project.order.domain.entity.Orders;
import com.project.order.domain.exception.InvalidValueException;
import com.project.order.domain.validation.OrderValidator;
import com.project.order.infrastructure.persistence.OrderRepository;
import com.project.order.presentation.dto.error.ErrorDetail;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;

    public OrderService(OrderRepository orderRepository, OrderValidator orderValidator) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
    }

    public Orders createOrder(
            OrderStatus status,
            String consumerId,
            int quantity,
            String note,
            String properties,
            ProductResponseDto product
    ) {

        boolean isQuantityValid = this.orderValidator.isQuantityValid(quantity, product.quantity());
        if (!isQuantityValid) {
            ErrorDetail errorDetail = new ErrorDetail("quantity", "invalid quantity");
            throw new InvalidValueException("quantity is not valid", errorDetail);
        }

        var order = new Orders();
        order.setAmount(quantity * product.price());
        order.setStatus(status);
        order.setConsumer_id(consumerId);
        order.setProduct_id(product.id());
        order.setQuantity(quantity);
        order.setNote(note);
        order.setProperties(properties);

        this.orderRepository.save(order);
        return order;
    }
}
