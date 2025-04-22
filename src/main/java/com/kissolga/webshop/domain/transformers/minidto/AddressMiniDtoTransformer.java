package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.AddressMiniDto;
import com.kissolga.webshop.domain.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMiniDtoTransformer {
    public AddressMiniDto transform(Address address) {
        AddressMiniDto result = new AddressMiniDto();

        result.setId(address.getId());
        result.setStreet(address.getStreet());
        result.setCity(address.getCity());
        result.setHouseNumber(address.getHouseNumber());
        result.setCountry(result.getCountry());
        result.setPostalCode(address.getPostalCode());

        return result;
    }
}
