package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.PaymentMethodMiniDto;
import com.kissolga.webshop.domain.transformers.minidto.PaymentMethodMiniDtoTransformer;
import com.kissolga.webshop.repositories.PaymentMethodRepository;
import com.kissolga.webshop.services.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/payment-methods")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;
    @GetMapping("/get-all")
    public List<PaymentMethodMiniDto> findAll() {
        return paymentMethodService.findAll();
    }
}
