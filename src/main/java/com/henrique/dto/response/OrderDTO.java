package com.henrique.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private LocalDateTime date;
    private String status;
    private List<OrderItemDTO> items;
}
