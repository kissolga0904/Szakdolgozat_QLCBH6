package com.kissolga.webshop.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

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
    private OrderStatuses status;

    @ManyToOne
    @JoinColumn(name = "shipping_method_id")
    private ShippingMethods shippingMethods;

    @ManyToOne
    @JoinColumn(name = "payment_method_id")
    private PaymentMethods paymentMethods;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Addresses addresses;

    @ManyToOne
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCarts shoppingCarts;
}
