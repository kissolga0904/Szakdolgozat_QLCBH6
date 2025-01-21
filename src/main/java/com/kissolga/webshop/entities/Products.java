package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Products {

    @Id
    @GeneratedValue
    private int id;

    @OneToMany(mappedBy = "id")
    private List<Reviews> reviews;

    @Column(nullable = false)
    private String name;

    private double price;

    private String description;
}
