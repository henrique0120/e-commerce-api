package com.henrique.service.query;

import com.henrique.dto.response.ProductDTO;

import java.util.List;

public interface IProductQueryService {

    void verifyName(final String name);

    ProductDTO findProduct (final String Name);

    List<ProductDTO> findByPrice (final Double price);

    List<ProductDTO> findByCategory (final String category);

    List<ProductDTO> findAll();
}
