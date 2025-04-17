package com.henrique.controller;


import com.henrique.dto.request.CreateOrderRequest;
import com.henrique.dto.response.OrderDTO;
import com.henrique.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody CreateOrderRequest request,
                                                @RequestParam Long userId) {
        OrderDTO createdOrder = orderService.createOrder(request, userId);
        return ResponseEntity.ok(createdOrder);
    }
}
