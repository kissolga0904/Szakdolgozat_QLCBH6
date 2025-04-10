package com.kissolga.webshop.domain.dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProductMiniDto {
    private int id;
    private String name;
    private double price;
    private String description;
    private String filename;
}
