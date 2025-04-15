package com.henrique.dto;


import lombok.Data;

@Data
public class CartItemDTO {
    private Long productId;
    private ProductDTO product;
    private int quantity;

}
