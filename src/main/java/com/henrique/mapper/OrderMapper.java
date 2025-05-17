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

    public static OrderDTO toDTO(OrderEntity orders) {
        OrderDTO dto = new OrderDTO();
        dto.setId(orders.getId());
        dto.setDate(orders.getDate());
        dto.setStatus(orders.getStatus());
        dto.setItems(orders.getItems().stream().map(OrderMapper::toItemDTO).collect(Collectors.toList()));
        return dto;
    }

    public static OrderItemDTO toItemDTO(OrderItem item) {
        OrderItemDTO dto = new OrderItemDTO();
        dto.setProductId(item.getProduct().getId());
        dto.setQuantity(item.getQuantity());

        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(item.getProduct().getName());
        productDTO.setPrice(item.getPriceAtPurchase());

        dto.setProduct(productDTO);
        return dto;
    }

}
