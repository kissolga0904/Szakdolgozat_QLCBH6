package com.kissolga.webshop.domain.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class PlaceOrderRequest {
    private int shippingMethodId;
    private int paymentMethodId;
    private String street;
    private int houseNumber;
    private String city;
    private int postalCode;
    private String country;
}
