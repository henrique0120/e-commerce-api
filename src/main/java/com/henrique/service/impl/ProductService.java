package com.henrique.service.impl;

import com.henrique.model.ProductEntity;
import com.henrique.repository.ProductRepository;
import com.henrique.service.IProductService;
import com.henrique.service.query.IProductQueryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService implements IProductService {
    private final ProductRepository repository;
    private final IProductQueryService queryService;

    @Override
    public void createProduct(final ProductEntity product){
        queryService.verifyName(product.getName());

        repository.save(product);
    }

    public void deleteProduct(Long id){
        repository.deleteById(id);
    }

}
