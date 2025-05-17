package com.henrique.service.query.impl;


import com.henrique.dto.response.ProductDTO;
import com.henrique.exception.NameInUseException;
import com.henrique.mapper.ProductMapper;
import com.henrique.model.ProductEntity;
import com.henrique.repository.ProductRepository;
import com.henrique.service.query.IProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductQueryService implements IProductQueryService {

    private ProductRepository repository;
    private ProductMapper productMapper;

    @Override
    public void verifyName(final String name) {
        if (repository.existsByName(name)) {
            var message = "O produto " + name + " já está em uso";
            throw new NameInUseException(message);
        }
    }

    @Override
    public ProductDTO findProduct(String name) {
        ProductEntity product = repository.findByName(name);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> findByPrice(Double price){
        List<ProductEntity> product = repository.findByPrice(price);
        return productMapper.toDtoList(product);
    }

    @Override
    public List<ProductDTO> findByCategory(String category){
        List<ProductEntity> product = repository.findByCategory(category);
        return productMapper.toDtoList(product);
    }

    @Override
    public List<ProductDTO> findAll() {
        List<ProductEntity> products = repository.findAll();
        return productMapper.toDtoList(products);
    }

}
