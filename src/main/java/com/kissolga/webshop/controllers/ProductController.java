package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.entity.ProductTransformer;
import com.kissolga.webshop.domain.transformers.minidto.ProductMiniDtoTransformer;
import com.kissolga.webshop.repositories.ProductRepository;
import com.kissolga.webshop.services.FileUploaderService;
import com.kissolga.webshop.services.ProductService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    private final ProductService productService;

    @GetMapping("/get-all")
    public List<ProductMiniDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/find-random-products")
    public List<ProductMiniDto> findRandomProducts() {
        return productService.findRandomProducts();
    }

    @GetMapping("/find-by-id/{id}")
    public ProductMiniDto findProductById(@PathVariable int id) {
        return productService.findProductById(id);
    }

    @PostMapping("/create")
    public ProductMiniDto createProduct(@RequestBody ProductMiniDto productMiniDto) {
        return productService.createProduct(productMiniDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@PathVariable int id) {
        productService.deleteProduct(id);
    }

    @PutMapping("/modify")
    public void modify(@RequestBody ProductMiniDto productMiniDto) {
        productService.modify(productMiniDto);
    }
}
