package com.kissolga.webshop.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ShoppingCarts")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "shoppingCart")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String ip;

    @OneToMany(mappedBy = "cart")
    private List<CartProduct> products;
}
