package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCartDto {
    private int id;
    private UserMiniDto user;
    private List<CartProductDto> products;
}

