package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductMiniDtoTransformer {
    public ProductMiniDto transform(Product product) {
        return ProductMiniDto.builder()
                .id(product.getId())
                .description(product.getDescription())
                .price(product.getPrice())
                .name(product.getName())
                .filename(product.getFilename())
                .quantity(product.getQuantity())
                .build();
    }

    public List<ProductMiniDto> transform(List<Product> products) {
        return products.stream().map(this::transform).collect(Collectors.toList());
    }
}
