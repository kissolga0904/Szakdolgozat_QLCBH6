package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDto {
    private int id;
    private List<ReviewMiniDto> reviews;
    private String name;
    private double price;
    private String description;
    private int quantity;
}
