package com.kissolga.webshop.domain.transformers.dto;

import com.kissolga.webshop.domain.dtos.ShoppingCartDto;
import com.kissolga.webshop.domain.dtos.ShoppingCartMiniDto;
import com.kissolga.webshop.domain.entities.ShoppingCart;
import com.kissolga.webshop.domain.transformers.minidto.UserMiniDtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartDtoTransformer {

    @Autowired
    private UserMiniDtoTransformer userMiniDtoTransformer;
    @Autowired
    private CartProductDtoTransformer cartProductDtoTransformer;

    public ShoppingCartDto transform(ShoppingCart shoppingCart) {
        ShoppingCartDto result = new ShoppingCartDto();

        result.setId(shoppingCart.getId());
        result.setProducts(cartProductDtoTransformer.transform(shoppingCart.getProducts()));
        result.setUser(userMiniDtoTransformer.transform(shoppingCart.getUser()));

        return result;
    }
}
