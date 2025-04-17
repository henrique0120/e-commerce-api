package com.henrique.dto.request;

import lombok.Data;

@Data
public class CreateOrderItemRequest {
    private Long productId;
    private int quantity;
}
