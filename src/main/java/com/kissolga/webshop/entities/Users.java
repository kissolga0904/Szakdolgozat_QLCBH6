package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Roles role;
}
