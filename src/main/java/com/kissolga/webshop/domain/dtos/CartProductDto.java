package com.kissolga.webshop.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CartProductDto {
    private int id;
    private int quantity;
    private double price;
    private ProductMiniDto product;
}