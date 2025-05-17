package com.henrique.mapper;

import com.henrique.controller.request.SaveProductRequest;
import com.henrique.controller.response.ProductDetailResponse;
import com.henrique.controller.response.SaveProductResponse;
import com.henrique.dto.response.ProductDTO;
import com.henrique.model.ProductEntity;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface ProductMapper {

    ProductDTO toDto(ProductEntity entity);

    SaveProductResponse toSaveResponse(final ProductEntity entity);

    @Mapping(target = "id", ignore = true)
    ProductDetailResponse toProductResponse(final ProductDTO dto);

    @Mapping(target = "id", ignore = true)
    ProductEntity toEntity(@Valid SaveProductRequest dto);

    List<ProductDTO> toDtoList(List<ProductEntity> entities);

    List<ProductEntity> toEntityList(List<ProductDTO> dtos);
}

