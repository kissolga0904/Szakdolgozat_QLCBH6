package com.kissolga.webshop.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RoleDto {
    private int id;
    private String name;
    private List<UserMiniDto> users;
}
