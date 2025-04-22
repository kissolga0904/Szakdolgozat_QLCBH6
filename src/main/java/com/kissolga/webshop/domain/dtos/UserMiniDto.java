package com.kissolga.webshop.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserMiniDto {
    private int id;
    private String username;
    private String password;
    private String email;
    private RoleDto role;
}
