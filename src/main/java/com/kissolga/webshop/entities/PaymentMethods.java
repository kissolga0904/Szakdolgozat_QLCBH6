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
public class PaymentMethods {

    @Id
    @GeneratedValue
    private int id;

    private String paymentName;

    @OneToMany(mappedBy = "paymentMethods")
    private List<Orders> orders;
}
