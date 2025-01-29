package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewMiniDto {
    private int id;
    private int rating;
    private String comment;
}
