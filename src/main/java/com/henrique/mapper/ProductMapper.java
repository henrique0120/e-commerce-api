package com.henrique.mapper;

import com.henrique.dto.ProductDTO;
import com.henrique.entity.ProductEntity;
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
        dto.setType(entity.getType());
        dto.setPrice(entity.getPrice());
        return dto;
    }

    public List<ProductDTO> toDtoList(List<ProductEntity> entities) {
        if (entities == null || entities.isEmpty()) return new ArrayList<>();

        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public ProductEntity toEntity(ProductDTO dto) {
        if (dto == null) return null;

        ProductEntity entity = new ProductEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setType(dto.getType());
        entity.setPrice(dto.getPrice());
        return entity;
    }
}

