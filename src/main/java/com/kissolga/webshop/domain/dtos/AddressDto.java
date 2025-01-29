package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressDto {
    private int id;
    private String street;
    private int houseNumber;
    private String city;
    private int postalCode;
    private String country;
    private List<OrderMiniDto> orders;
    private List<UserMiniDto> users;
}
