package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleDto {
    private int id;
    private String name;
    private List<UserMiniDto> users;
}
