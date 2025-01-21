package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class ShippingMethods {
    @Id
    @GeneratedValue
    private int id;

    private String name;

    private double price;

    @OneToMany(mappedBy = "shippingMethods")
    private List<Orders> orders;
}
