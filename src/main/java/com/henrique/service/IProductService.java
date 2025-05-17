package com.henrique.service;

import com.henrique.model.ProductEntity;

public interface IProductService {

    void createProduct(final ProductEntity entity);

    void deleteProduct(final Long id);


}
