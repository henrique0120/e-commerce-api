package com.henrique.controller;

import com.henrique.controller.request.SaveProductRequest;
import com.henrique.controller.response.ProductDetailResponse;
import com.henrique.controller.response.SaveProductResponse;
import com.henrique.dto.response.ProductDTO;
import com.henrique.mapper.ProductMapper;
import com.henrique.service.impl.ProductService;
import com.henrique.service.query.impl.ProductQueryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductQueryService productQuery;
    private final ProductMapper mapper;

    @PostMapping("/create")
    @ResponseStatus(CREATED)
    SaveProductResponse createProduct(@RequestBody @Valid final SaveProductRequest request){
        var entity = mapper.toEntity(request);
        productService.createProduct(entity);
        return mapper.toSaveResponse(entity);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
    }

    @GetMapping("")
    public ResponseEntity<List<ProductDTO>> findAll() {
        List<ProductDTO> products = productQuery.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/find/category/{category}")
    public ResponseEntity<List<ProductDTO>>findByCategory(@PathVariable String category){
        List<ProductDTO> product = productQuery.findByCategory(category);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/find/price/{price}")
    public ResponseEntity<List<ProductDTO>>findByPrice(@PathVariable Double price){
        List<ProductDTO> product = productQuery.findByPrice(price);
        return ResponseEntity.ok(product);
    }


    @GetMapping("/find/{name}")
    ProductDetailResponse findProduct(@PathVariable String name){
        var entity = productQuery.findProduct(name);
        return mapper.toProductResponse(entity);
    }



}
