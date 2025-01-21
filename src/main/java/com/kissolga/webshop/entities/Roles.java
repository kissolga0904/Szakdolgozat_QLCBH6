package com.kissolga.webshop.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Roles {

    @Id
    @GeneratedValue
    private int id;

    private String name;

    @OneToMany(mappedBy = "role")
    private List<Users> users;
}
