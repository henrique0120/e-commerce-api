package com.henrique.mapper;

import com.henrique.dto.CartItemDTO;
import com.henrique.entity.CartItem;
import com.henrique.entity.ProductEntity;
import com.henrique.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CartItemMapper {

    private final ProductRepository productRepository;

    public CartItemMapper(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public CartItem toEntity(CartItemDTO dto) {
        CartItem item = new CartItem();
        ProductEntity product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        item.setProduct(product);
        item.setQuantity(dto.getQuantity());
        return item;
    }

    public CartItemDTO toDto(CartItem entity) {
        CartItemDTO dto = new CartItemDTO();
        dto.setProductId(entity.getProduct().getId());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }

    public List<CartItem> toEntityList(List<CartItemDTO> dtos) {
        List<CartItem> list = new ArrayList<>();
        for (CartItemDTO dto : dtos) {
            list.add(toEntity(dto));
        }
        return list;
    }

    public List<CartItemDTO> toDtoList(List<CartItem> entities) {
        List<CartItemDTO> list = new ArrayList<>();
        for (CartItem entity : entities) {
            list.add(toDto(entity));
        }
        return list;
    }
}
