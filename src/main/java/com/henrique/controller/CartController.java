package com.henrique.controller;

import com.henrique.config.AuthenticatedUserProvider;
import com.henrique.dto.response.CartDTO;
import com.henrique.dto.response.CartItemDTO;
import com.henrique.model.CartEntity;
import com.henrique.model.UserEntity;
import com.henrique.mapper.CartMapper;
import com.henrique.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService service;
    private final CartMapper cartMapper;
    private final AuthenticatedUserProvider authUser;

    public CartController(CartService service, CartMapper cartMapper, AuthenticatedUserProvider authUser) {
        this.service = service;
        this.cartMapper = cartMapper;
        this.authUser = authUser;
    }

    @GetMapping("")
    public ResponseEntity<CartDTO> getMyCart(HttpServletRequest request) {
        UserEntity user = authUser.getUserFromToken(request);
        CartEntity cart = service.getCartByUser(user);
        return ResponseEntity.ok(cartMapper.toDto(cart));
    }

    @PostMapping("/add-product")
    public ResponseEntity<CartDTO> addProductToCart(@RequestBody CartItemDTO itemDTO, HttpServletRequest request) {
        UserEntity user = authUser.getUserFromToken(request);
        service.addProductToCart(user, itemDTO.getProductId(), itemDTO.getQuantity());

        CartEntity updatedCart = service.getCartByUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(cartMapper.toDto(updatedCart));
    }

    @PostMapping("/add-quantity")
    public ResponseEntity<CartDTO> addQuantityToCart(@RequestBody CartItemDTO itemDTO, HttpServletRequest request){
        UserEntity user = authUser.getUserFromToken(request);
        service.addQuantityToCart(user, itemDTO.getProductId(), itemDTO.getQuantity());

        CartEntity updatedCart = service.getCartByUser(user);
        return ResponseEntity.ok(cartMapper.toDto(updatedCart));
    }

    @DeleteMapping("/remove-quantity")
    public ResponseEntity<CartDTO> removeQuantityFromCart(@RequestBody CartItemDTO itemDTO, HttpServletRequest request) {
        UserEntity user = authUser.getUserFromToken(request);
        service.delQuantityFromCart(user, itemDTO.getProductId(), itemDTO.getQuantity());

        CartEntity updatedCart = service.getCartByUser(user);
        return ResponseEntity.ok(cartMapper.toDto(updatedCart));
    }


    @DeleteMapping("/remove-product")
    public ResponseEntity<CartDTO> removeProductFromCart(@RequestBody CartItemDTO itemDTO, HttpServletRequest request) {
        UserEntity user = authUser.getUserFromToken(request);
        service.delProductFromCart(user, itemDTO.getProductId());

        CartEntity updatedCart = service.getCartByUser(user);
        return ResponseEntity.ok(cartMapper.toDto(updatedCart));
    }


}
