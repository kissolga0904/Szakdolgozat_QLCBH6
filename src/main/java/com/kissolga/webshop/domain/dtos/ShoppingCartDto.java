package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCartDto {
    private int id;
    private List<OrderMiniDto> orders;
    private UserMiniDto user;
}

