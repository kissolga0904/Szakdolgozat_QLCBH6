package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.minidto.ProductMiniDtoTransformer;
import com.kissolga.webshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMiniDtoTransformer productMiniDtoTransformer;

    @GetMapping("/find-random-products")
    public List<ProductMiniDto> findRandomProducts() {
        return productRepository.findAll()
                .stream()
                .sorted((e1, e2) -> new Random().nextInt(2) - 1)
                .limit(10)
                .map(p -> ProductMiniDto.builder()
                        .id(p.getId())
                        .name(p.getName())
                        .price(p.getPrice())
                        .description(p.getDescription())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-id/{id}")
    public ProductMiniDto findProductById(@PathVariable int id) {
        return productRepository.findById(id)
                .map(product -> productMiniDtoTransformer.transform(product))
                .orElseThrow(() -> new RuntimeException("No product found with this id."));
    }
}

