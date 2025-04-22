package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.ShippingMethodMiniDto;
import com.kissolga.webshop.domain.entities.ShippingMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShippingMethodMiniDtoTransformer {

    public ShippingMethodMiniDto transform(ShippingMethod method) {
        ShippingMethodMiniDto result = new ShippingMethodMiniDto();

        result.setId(method.getId());
        result.setName(method.getName());
        result.setPrice(method.getPrice());

        return result;
    }

    public List<ShippingMethodMiniDto> transform(List<ShippingMethod> shippingMethods) {
        return shippingMethods.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}