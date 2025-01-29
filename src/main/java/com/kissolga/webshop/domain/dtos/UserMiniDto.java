package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserMiniDto {
    private int id;
    private String username;
    private String password;
    private String email;
}
