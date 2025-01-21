package com.kissolga.webshop.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Products {

    @Id
    @GeneratedValue
    private int id;

    @Column(nullable = false)
    private String name;

    private double price;

    private String description;
}
