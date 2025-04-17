package com.henrique.service;



import com.henrique.dto.request.CreateOrderItemRequest;
import com.henrique.dto.request.CreateOrderRequest;
import com.henrique.dto.response.OrderDTO;
import com.henrique.mapper.OrderMapper;
import com.henrique.model.OrderEntity;
import com.henrique.model.OrderItem;
import com.henrique.model.ProductEntity;
import com.henrique.model.UserEntity;
import com.henrique.repository.OrderRepository;
import com.henrique.repository.ProductRepository;
import com.henrique.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository; // se quiser associar a um usuário

    @Transactional
    public OrderDTO createOrder(CreateOrderRequest request, Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setStatus("PENDING");

        List<OrderItem> items = new ArrayList<>();

        for (CreateOrderItemRequest itemRequest : request.getItems()) {
            ProductEntity product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: ID " + itemRequest.getProductId()));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPriceAtPurchase(product.getPrice());
            item.setSubtotal(product.getPrice().multiply(
                    new java.math.BigDecimal(itemRequest.getQuantity()))
            );
            item.setOrder(order);
            items.add(item);
        }

        order.setItems(items);
        orderRepository.save(order); // salva order e items (graças ao Cascade.ALL)

        return OrderMapper.toDTO(order);
    }
}
