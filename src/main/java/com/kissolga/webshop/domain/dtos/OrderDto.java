package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrderDto {
    private int id;
    private Date orderDate;
    private OrderStatusMiniDto status;
    private ShippingMethodMiniDto shippingMethod;
    private PaymentMethodMiniDto paymentMethod;
    private AddressMiniDto address;
    private ShoppingCartMiniDto shoppingCart;
}
