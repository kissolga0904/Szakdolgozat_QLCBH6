package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.PaymentMethodMiniDto;
import com.kissolga.webshop.domain.entities.PaymentMethod;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodMiniDtoTransformer {

    public PaymentMethodMiniDto transform(PaymentMethod paymentMethod) {
        PaymentMethodMiniDto paymentMethodMiniDto = new PaymentMethodMiniDto();

        paymentMethodMiniDto.setId(paymentMethod.getId());
        paymentMethodMiniDto.setName(paymentMethod.getName());

        return paymentMethodMiniDto;
    }

    public List<PaymentMethodMiniDto> transform(List<PaymentMethod> methods) {
        return methods.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}
