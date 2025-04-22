package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.PaymentMethodMiniDto;
import com.kissolga.webshop.domain.transformers.minidto.PaymentMethodMiniDtoTransformer;
import com.kissolga.webshop.repositories.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodService {
    @Autowired
    private PaymentMethodMiniDtoTransformer paymentMethodMiniDtoTransformer;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    public List<PaymentMethodMiniDto> findAll() {
        return paymentMethodMiniDtoTransformer.transform(paymentMethodRepository.findAll());
    }
}
