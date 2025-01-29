package com.kissolga.webshop.domain.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderStatusDto {
    private int id;
    private String status;
    private List<OrderMiniDto> orders;
}
