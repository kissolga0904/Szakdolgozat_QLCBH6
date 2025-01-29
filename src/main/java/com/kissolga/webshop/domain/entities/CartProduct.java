package com.kissolga.webshop.domain.entities;

import com.kissolga.webshop.domain.dtos.ProductDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "CartProducts")
public class CartProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //mit kossek mibe?
    //mindegyik cart product egy termek a kosaramban
    //egy produvctot tartalmazzon
    //egy productnak lehet tobb cartproductja
    //egy a többhöz - Productba

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;

    private double price;
}
//TODO