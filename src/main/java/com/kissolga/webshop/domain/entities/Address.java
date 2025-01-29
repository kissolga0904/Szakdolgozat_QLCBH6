package com.kissolga.webshop.domain.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String street;

    private int houseNumber;

    private String city;

    private int postalCode;

    private String country;

    @OneToMany(mappedBy = "address")
    private List<Order> orders;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
