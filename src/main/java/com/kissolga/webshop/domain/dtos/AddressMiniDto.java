package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressMiniDto {
    private int id;
    private String street;
    private int houseNumber;
    private String city;
    private int postalCode;
    private String country;
}
