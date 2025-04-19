package com.henrique.controller;


import com.henrique.dto.request.CreateOrderItemRequest;
import com.henrique.dto.request.CreateOrderRequest;
import com.henrique.dto.response.OrderDTO;
import com.henrique.dto.response.OrderItemDTO;
import com.henrique.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody List<CreateOrderItemRequest> items) {
        OrderDTO createdOrder = orderService.createOrder(items);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
    }
}
