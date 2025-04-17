package com.henrique.mapper;

import com.henrique.dto.response.OrderDTO;
import com.henrique.dto.response.OrderItemDTO;
import com.henrique.dto.response.ProductDTO;
import com.henrique.model.OrderEntity;
import com.henrique.model.OrderItem;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderMapper {

    public static OrderDTO toDTO(OrderEntity order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setDate(order.getDate());
        dto.setStatus(order.getStatus().toString());
        dto.setItems(order.getItems().stream().map(OrderMapper::toItemDTO).collect(Collectors.toList()));
        return dto;
    }

    public static OrderItemDTO toItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(item.getProduct().getId());
        productDTO.setName(item.getProduct().getName());
        productDTO.setPrice(item.getPriceAtPurchase());

        dto.setProduct(productDTO);
        return dto;
    }

}
