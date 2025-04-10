package com.kissolga.webshop.domain.transformers.entity;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductTransformer {

    public Product transform(ProductMiniDto productMiniDto) {
        Product product = new Product();

        product.setName(productMiniDto.getName());
        product.setPrice(productMiniDto.getPrice());
        product.setDescription(productMiniDto.getDescription());
        product.setFilename(productMiniDto.getFilename());

        return product;
    }
}
