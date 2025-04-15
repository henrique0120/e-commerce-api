package com.henrique.mapper;

import com.henrique.dto.CartDTO;
import com.henrique.dto.CartItemDTO;
import com.henrique.dto.ProductDTO;
import com.henrique.dto.UserDTO;
import com.henrique.entity.CartEntity;
import com.henrique.entity.CartItem;
import com.henrique.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CartMapper {

    private final ProductMapper productMapper;

    public CartMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    public CartDTO toDto(CartEntity entity) {
        if (entity == null) return null;

        CartDTO dto = new CartDTO();
        dto.setId(entity.getId());

        List<CartItemDTO> itemDTOs = entity.getItems().stream()
                .map(item -> {
                    CartItemDTO dtoItem = new CartItemDTO();
                    dtoItem.setProductId(item.getId());
                    dtoItem.setQuantity(item.getQuantity());
                    dtoItem.setProduct(productMapper.toDto(item.getProduct()));
                    return dtoItem;
                }).toList();

        dto.setItems(itemDTOs);

        return dto;
    }

    public CartEntity toEntity(CartDTO dto) {
        return null;
    }
}


