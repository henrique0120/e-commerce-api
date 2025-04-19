package com.henrique.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {
    private Long userId;
    private List<CreateOrderItemRequest> items;
}
