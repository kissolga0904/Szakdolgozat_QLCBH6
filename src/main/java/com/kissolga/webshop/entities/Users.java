package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "id")
    private List<Reviews> reviews;

    @OneToMany(mappedBy = "id")
    private List<ShoppingCarts> shoppingCarts;

    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}
