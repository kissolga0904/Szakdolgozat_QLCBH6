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
public class Addresses {
    @Id
    @GeneratedValue
    private int id;

    private String street;

    private int houseNumber;

    private String city;

    private int postalCode;

    private String country;

    @OneToMany(mappedBy = "addresses")
    private List<Orders> orders;
}
