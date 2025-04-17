package com.henrique.repository;

import com.henrique.model.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    ProductEntity findByName( String name);
    List<ProductEntity> findByPrice(Double price);
    List<ProductEntity> findByCategory(String category);
    List<ProductEntity> findAll();
}
