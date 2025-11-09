package com.project.order.domain.validation;

import com.project.order.adapter.restApi.dto.error.ErrorDetail;
import com.project.order.adapter.grpc.dto.ProductResponseDto;
import com.project.order.domain.exception.InvalidRequestCreateOrder;
import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    public void validateQuantity(int quantity, int productQuantity) {
        if ((quantity <= 0 || quantity > productQuantity) ) {
            ErrorDetail errorDetail = new ErrorDetail("quantity", "invalid quantity");
            throw new InvalidRequestCreateOrder("quantity is not valid", errorDetail);
        }
    }

    public void validateRequestCreateOrder(int quantity, ProductResponseDto product) {
        this.validateQuantity(quantity, product.quantity());
    }

}
