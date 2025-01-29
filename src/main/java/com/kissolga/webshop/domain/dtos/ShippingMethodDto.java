package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShippingMethodDto {
    private int id;
    private String name;
    private double price;
    private List<OrderMiniDto> orders;
}
