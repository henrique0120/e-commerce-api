package com.henrique.controller;

import com.henrique.dto.response.ProductDTO;
import com.henrique.model.ProductEntity;
import com.henrique.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductEntity productEntity){
        productService.createProduct(productEntity);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/find/category/{category}")
    public ResponseEntity<List<ProductDTO>>findByCategory(@PathVariable String category){
        List<ProductDTO> product = productService.findByCategory(category);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/find/price/{price}")
    public ResponseEntity<List<ProductDTO>>findByPrice(@PathVariable Double price){
        List<ProductDTO> product = productService.findByPrice(price);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/find/{name}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable String name){
        ProductDTO product = productService.findProduct(name);
        return ResponseEntity.ok(product);
    }



}
