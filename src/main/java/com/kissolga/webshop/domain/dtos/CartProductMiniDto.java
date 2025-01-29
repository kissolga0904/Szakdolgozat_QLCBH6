package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartProductMiniDto {
    private int id;
    private int quantity;
    private double price;
}