package com.project.order.adapter.restApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.order.adapter.restApi.dto.request.CreateOrderDto;
import com.project.order.adapter.restApi.dto.response.SuccessResponse;
import com.project.order.adapter.grpc.client.ProductServiceClient;
import com.project.order.domain.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class OrderController {
    private final OrderService orderService;
    private final ProductServiceClient productServiceClient;

    public OrderController(OrderService orderService, ProductServiceClient productServiceClient) {
        this.orderService = orderService;
        this.productServiceClient = productServiceClient;
    }

    @PostMapping("/v1/orders")
    public ResponseEntity<?> createOrder(@RequestBody CreateOrderDto dto, HttpServletRequest request)
            throws JsonProcessingException
    {
        var product = productServiceClient.getProductById(dto.productId());
        var order = this.orderService.createOrder(dto.status(), request.getHeader("X-User-Id"),
                dto.quantity(), dto.note(), dto.properties(), product);

        var response = new SuccessResponse<>("Order created successfully", order);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/v1/users/{id}/orders")
    public ResponseEntity<?> getOrders(@PathVariable String id, HttpServletRequest request) {
        var userId = request.getHeader("X-User-Id");
        if (!Objects.equals(id, userId)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        var orders = this.orderService.getOrdersByUserId(userId);
        var response = new SuccessResponse<>("Success", orders);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
