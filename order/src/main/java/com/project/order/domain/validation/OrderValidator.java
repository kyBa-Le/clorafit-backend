package com.project.order.domain.validation;

import org.springframework.stereotype.Component;

@Component
public class OrderValidator {

    public boolean isQuantityValid(int quantity, int productQuantity) {
        return quantity > 0 && quantity <= productQuantity;
    }

}
