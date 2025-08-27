package com.project.order.adapter.restApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.order.adapter.messaging.producer.OrderProducer;
import com.project.order.adapter.restApi.dto.request.CreateOrderDto;
import com.project.order.adapter.restApi.dto.response.SuccessResponse;
import com.project.order.domain.grpcService.ProductServiceClient;
import com.project.order.domain.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final ProductServiceClient productServiceClient;
    private final OrderProducer orderProducer;

    public OrderController(OrderService orderService, ProductServiceClient productServiceClient, OrderProducer orderProducer) {
        this.orderService = orderService;
        this.productServiceClient = productServiceClient;
        this.orderProducer = orderProducer;
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto dto) throws JsonProcessingException {
        var product = productServiceClient.getProductById(dto.productId());
        var order = this.orderService.createOrder(dto.status(), dto.consumerId(), dto.quantity(), dto.note(), dto.properties(), product);
        this.orderProducer.orderCreatedEvent(order.getProduct_id(), dto.quantity());
        var response = new SuccessResponse<>("Order created successfully", order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
