package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.ShoppingCartMiniDto;
import com.kissolga.webshop.domain.entities.ShoppingCart;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartMiniDtoTransformer {

    public ShoppingCartMiniDto transform(ShoppingCart cart) {
        ShoppingCartMiniDto cartDto = new ShoppingCartMiniDto();

        cartDto.setId(cart.getId());

        return cartDto;
    }
}

