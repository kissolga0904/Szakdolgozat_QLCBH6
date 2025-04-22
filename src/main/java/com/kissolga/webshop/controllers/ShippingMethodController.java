package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.ShippingMethodMiniDto;
import com.kissolga.webshop.domain.entities.ShippingMethod;
import com.kissolga.webshop.domain.transformers.minidto.ShippingMethodMiniDtoTransformer;
import com.kissolga.webshop.repositories.ShippingMethodRepository;
import com.kissolga.webshop.services.ShippingMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/shipping-methods")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class ShippingMethodController {
    private final ShippingMethodService shippingMethodService;

    @GetMapping("/get-all")
    public List<ShippingMethodMiniDto> findAllShippingMethods() {
        return  shippingMethodService.findAllShippingMethods();
    }

}
