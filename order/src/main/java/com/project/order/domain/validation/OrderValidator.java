package com.project.order.domain.validation;

import com.project.order.adapter.restApi.dto.error.ErrorDetail;
import com.project.order.domain.dto.ProductResponseDto;
import com.project.order.domain.entity.OrderStatus;
import com.project.order.domain.exception.InvalidRequestCreateOrder;
import com.project.order.infrastructure.persistence.OrderRepository;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {
    private final OrderRepository orderRepository;

    public OrderValidator(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void validateQuantity(int quantity, int productQuantity) {
        if ((quantity <= 0 || quantity > productQuantity) ) {
            ErrorDetail errorDetail = new ErrorDetail("quantity", "invalid quantity");
            throw new InvalidRequestCreateOrder("quantity is not valid", errorDetail);
        }
    }

    public void validateNoDuplicateDraft(String userId, String productId, OrderStatus status) {
        if (orderRepository.existsByConsumerIdAndProductIdAndStatus(userId, productId, OrderStatus.DRAFT)
                && status.equals(OrderStatus.DRAFT)) {
            ErrorDetail errorDetail = new ErrorDetail("product", "the product is already existed in cart");
            throw new InvalidRequestCreateOrder("product is not valid", errorDetail);
        }
    }

    public void validateRequestCreateOrder(String userId, int quantity, OrderStatus status, ProductResponseDto product) {
        this.validateQuantity(quantity, product.quantity());
        this.validateNoDuplicateDraft(userId, product.id(), status);
    }

}
