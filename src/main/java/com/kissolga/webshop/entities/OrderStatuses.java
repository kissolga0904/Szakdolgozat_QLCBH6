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
public class OrderStatuses {

    @Id
    @GeneratedValue
    private int id;

    private String status;

    @OneToMany(mappedBy = "status")
    private List<Orders> orders;
}
