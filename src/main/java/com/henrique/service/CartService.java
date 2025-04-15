package com.henrique.service;

import com.henrique.entity.CartEntity;
import com.henrique.entity.CartItem;
import com.henrique.entity.ProductEntity;
import com.henrique.entity.UserEntity;
import com.henrique.repository.CartItemRepository;
import com.henrique.repository.CartRepository;
import com.henrique.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final CartItemRepository cartItemRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, CartItemRepository cartItemRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public CartEntity getCartByUser(UserEntity user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    CartEntity newCart = new CartEntity();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });
    }

    @Transactional
    public void addProductToCart(UserEntity user, Long productId, int quantity) {
        CartEntity cart = getCartByUser(user);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cart.getItems().add(newItem);
        }

        cartRepository.save(cart); // salvar alterações
    }

    @Transactional
    public void delProductFromCart(UserEntity user, Long productId) {
        CartEntity cart = getCartByUser(user);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            // Remove completamente o item do carrinho
            cart.getItems().remove(existingItem);
            cartItemRepository.delete(existingItem);

            cartRepository.save(cart); // salva o carrinho atualizado
        } else {
            throw new RuntimeException("Produto não está no carrinho");
        }
    }

    @Transactional
    public void delQuantityFromCart(UserEntity user, Long productId, int quantity) {
        CartEntity cart = getCartByUser(user);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {
            if (existingItem.getQuantity() <= quantity) {
                // Remove completamente o item do carrinho
                cart.getItems().remove(existingItem);
                cartItemRepository.delete(existingItem);
            } else {
                // Apenas diminui a quantidade
                existingItem.setQuantity(existingItem.getQuantity() - quantity);
            }

            cartRepository.save(cart); // salva o carrinho atualizado
        } else {
            throw new RuntimeException("Produto não está no carrinho");
        }
    }

    @Transactional
    public void addQuantityToCart(UserEntity user, Long productId, int quantity) {
        CartEntity cart = getCartByUser(user);

        ProductEntity product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        CartItem existingItem = cart.getItems().stream()
                .filter(item -> item.getProduct().getId().equals(productId))
                .findFirst()
                .orElse(null);

        if (existingItem != null) {

            existingItem.setQuantity(existingItem.getQuantity() + quantity);

            cartRepository.save(cart);
        } else {
            throw new RuntimeException("Produto não está no carrinho");
        }
    }

}
