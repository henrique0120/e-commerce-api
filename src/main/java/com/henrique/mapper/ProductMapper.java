package com.henrique.mapper;

import com.henrique.dto.ProductDTO;
import com.henrique.model.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMapper {

    public ProductDTO toDto(ProductEntity entity) {
        if (entity == null) return null;

        ProductDTO dto = new ProductDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCategory(entity.getCategory());
        dto.setPrice(entity.getPrice());
        dto.setDesc(entity.getDescription());
        return dto;
    }

    public List<ProductDTO> toDtoList(List<ProductEntity> entities) {
        if (entities == null || entities.isEmpty()) return new ArrayList<>();

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }


}

