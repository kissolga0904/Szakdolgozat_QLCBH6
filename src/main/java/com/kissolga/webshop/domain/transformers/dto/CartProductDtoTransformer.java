package com.kissolga.webshop.domain.transformers.dto;

import com.kissolga.webshop.domain.dtos.CartProductDto;
import com.kissolga.webshop.domain.entities.CartProduct;
import com.kissolga.webshop.domain.transformers.minidto.ProductMiniDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartProductDtoTransformer {

    @Autowired
    private ProductMiniDtoTransformer productMiniDtoTransformer;

    public CartProductDto transform(CartProduct cartProduct) {
        return CartProductDto.builder()
                .id(cartProduct.getId())
                .price(cartProduct.getPrice())
                .quantity(cartProduct.getQuantity())
                .product(productMiniDtoTransformer.transform(cartProduct.getProduct()))
                .build();
    }

    public List<CartProductDto> transform(List<CartProduct> cartProducts) {
        if (cartProducts == null) {
            return new ArrayList<>();
        }

        return cartProducts.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}
