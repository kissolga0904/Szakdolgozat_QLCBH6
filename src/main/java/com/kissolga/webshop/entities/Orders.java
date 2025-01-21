package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class Orders {

    @Id
    @GeneratedValue
    private int id;

    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "order_status_id")
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethods shippingMethods;

}
