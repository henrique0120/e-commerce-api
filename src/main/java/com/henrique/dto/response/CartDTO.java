package com.henrique.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private List<CartItemDTO> items = new ArrayList<>();
}