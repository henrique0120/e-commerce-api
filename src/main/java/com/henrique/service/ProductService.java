package com.henrique.service;

import com.henrique.dto.ProductDTO;
import com.henrique.dto.UserDTO;
import com.henrique.entity.ProductEntity;
import com.henrique.entity.UserEntity;
import com.henrique.mapper.ProductMapper;
import com.henrique.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper productMapper;

    public ProductService(ProductRepository repository, ProductMapper productMapper) {
        this.repository = repository;
        this.productMapper = productMapper;
    }

    public void createProduct(ProductEntity product){
        repository.save(product);
    }

    public ProductDTO findProduct(String name) {
        ProductEntity product = repository.findByName(name);
        return productMapper.toDto(product);
    }

    public List<ProductDTO> findAll() {
        List<ProductEntity> products = repository.findAll();
        return productMapper.toDtoList(products);
    }
}
