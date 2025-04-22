package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.ShippingMethodMiniDto;
import com.kissolga.webshop.domain.transformers.minidto.ShippingMethodMiniDtoTransformer;
import com.kissolga.webshop.repositories.ShippingMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ShippingMethodService {
    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Autowired
    private ShippingMethodMiniDtoTransformer shippingMethodMiniDtoTransformer;


    public List<ShippingMethodMiniDto> findAllShippingMethods() {
        return  shippingMethodMiniDtoTransformer.transform(shippingMethodRepository.findAll());
    }
}
