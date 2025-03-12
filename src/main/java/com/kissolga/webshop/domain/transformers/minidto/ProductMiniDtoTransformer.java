package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMiniDtoTransformer {
    public ProductMiniDto transform(Product product) {
        return ProductMiniDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .build();
    }
}
