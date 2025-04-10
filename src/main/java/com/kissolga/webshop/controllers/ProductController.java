package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.entity.ProductTransformer;
import com.kissolga.webshop.domain.transformers.minidto.ProductMiniDtoTransformer;
import com.kissolga.webshop.repositories.ProductRepository;
import com.kissolga.webshop.services.FileUploaderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductRepository productRepository;
    private final ProductMiniDtoTransformer productMiniDtoTransformer;
    private final FileUploaderService fileUploaderService;
    private final ProductTransformer productTransformer;

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
                        .filename(p.getFilename())
                        .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/find-by-id/{id}")
    public ProductMiniDto findProductById(@PathVariable int id) {
        return productRepository.findById(id)
                .map(product -> productMiniDtoTransformer.transform(product))
                .orElseThrow(() -> new RuntimeException("No product found with this id."));
    }

    @PostMapping("/create")
    public ProductMiniDto createProduct(@RequestBody ProductMiniDto productMiniDto) {
        Product product = productTransformer.transform(productMiniDto);
        product.setFilename(
                fileUploaderService.upload(
                        product.getFilename()));

        product = productRepository.save(product);

        return productMiniDtoTransformer.transform(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productRepository.deleteById(id);
    }

    @PutMapping("/modify")
    public void modify(@RequestBody ProductMiniDto productMiniDto) {
        Optional<Product> product = productRepository.findById(productMiniDto.getId());

        if (product.isPresent()) {
            product.get().setDescription(productMiniDto.getDescription());
            product.get().setPrice(productMiniDto.getPrice());
            product.get().setName(productMiniDto.getName());

            if (productMiniDto.getFilename() != null && !productMiniDto.getFilename().isEmpty()) {
                product.get().setFilename(
                        fileUploaderService.upload(
                                productMiniDto.getFilename()));
            }

            productRepository.save(product.get());
        }
    }
}
