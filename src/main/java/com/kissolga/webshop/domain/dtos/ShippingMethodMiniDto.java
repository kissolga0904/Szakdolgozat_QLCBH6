package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShippingMethodMiniDto {
    private int id;
    private String name;
    private double price;
}
