package com.henrique.service;



import com.henrique.dto.request.CreateOrderItemRequest;
import com.henrique.dto.response.OrderDTO;
import com.henrique.mapper.OrderMapper;
import com.henrique.model.OrderEntity;
import com.henrique.model.OrderItem;
import com.henrique.model.ProductEntity;
import com.henrique.model.UserEntity;
import com.henrique.repository.OrderRepository;
import com.henrique.repository.ProductRepository;
import com.henrique.repository.UserRepository;
import com.henrique.service.query.impl.UserQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final UserQueryService service;
    private final OrderMapper orderMapper;

    public UserEntity getAuthenticatedUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return service.verifyUser(email);
    }

    @Transactional
    public OrderDTO createOrder(List<CreateOrderItemRequest> itemRequests) {
        UserEntity user = getAuthenticatedUser(); // Pega o usuário logado

        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setDate(LocalDateTime.now());
        order.setStatus("PENDING");

        List<OrderItem> items = new ArrayList<>();

        for (CreateOrderItemRequest itemRequest : itemRequests) {
            ProductEntity product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(product);
            item.setQuantity(itemRequest.getQuantity());
            item.setPriceAtPurchase(product.getPrice());
            item.setSubtotal(product.getPrice().multiply(BigDecimal.valueOf(itemRequest.getQuantity())));

            items.add(item);
        }

        order.setItems(items);

        OrderEntity savedOrder = orderRepository.save(order);
        return OrderMapper.toDTO(savedOrder);
    }
}
