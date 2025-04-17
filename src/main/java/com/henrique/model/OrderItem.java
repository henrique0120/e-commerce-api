package com.henrique.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "order")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "priceAtPurchase")
    private BigDecimal priceAtPurchase;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;


}
