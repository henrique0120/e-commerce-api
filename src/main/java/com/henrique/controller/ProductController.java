package com.henrique.controller;

import com.henrique.dto.ProductDTO;
import com.henrique.entity.ProductEntity;
import com.henrique.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService service, ProductService productService) {
        this.productService = productService;
    }



    @PostMapping("/create")
    public void createProduct(@RequestBody ProductEntity productEntity){
        productService.createProduct(productEntity);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/find/{name}")
    public ResponseEntity<ProductDTO> findProduct(@PathVariable String name){
        ProductDTO product = productService.findProduct(name);
        return ResponseEntity.ok(product);
    }



}
