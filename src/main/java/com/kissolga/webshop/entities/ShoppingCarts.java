package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ShoppingCarts {
    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "id")
    private List<Orders> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Users users;
}
