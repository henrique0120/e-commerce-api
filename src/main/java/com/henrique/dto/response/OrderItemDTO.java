package com.henrique.dto.response;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long id;
    private ProductDTO product;
    private int quantity;
}
