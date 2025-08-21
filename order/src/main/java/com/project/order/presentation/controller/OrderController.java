package com.project.order.presentation.controller;

import com.project.order.domain.grpcService.ProductServiceClient;
import com.project.order.domain.service.OrderService;
import com.project.order.presentation.dto.request.CreateOrderDto;
import com.project.order.presentation.dto.response.SuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.management.OperationsException;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final ProductServiceClient productServiceClient;

    public OrderController(OrderService orderService, ProductServiceClient productServiceClient) {
        this.orderService = orderService;
        this.productServiceClient = productServiceClient;
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto dto) {
        var product = productServiceClient.getProductById(dto.productId());
        var order = this.orderService.createOrder(dto.status(), dto.consumerId(), dto.quantity(), dto.note(), dto.properties(), product);
        var response = new SuccessResponse<>("Order created successfully", order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
