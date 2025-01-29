package com.kissolga.webshop.domain.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {
    private int id;
    private UserMiniDto user;
    private ProductMiniDto product;
    private int rating;
    private String comment;
}
