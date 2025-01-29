package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserDto {
    private int id;
    private String username;
    private String password;
    private String email;
    private RoleMiniDto role;
    private AddressMiniDto address;
    private List<ReviewMiniDto> reviews;
    private List<ShoppingCartMiniDto> shoppingCarts;
}
