package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.transformers.entity.ProductTransformer;
import com.kissolga.webshop.domain.transformers.minidto.ProductMiniDtoTransformer;
import com.kissolga.webshop.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMiniDtoTransformer productMiniDtoTransformer;
    private final FileUploaderService fileUploaderService;
    private final ProductTransformer productTransformer;

    public List<ProductMiniDto> getAllProducts() {
        return productMiniDtoTransformer.transform(productRepository.findAll());
    }

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
                        .quantity(p.getQuantity())
                        .build())
                .collect(Collectors.toList());
    }

    public ProductMiniDto findProductById(int id) {
        return productRepository.findById(id)
                .map(product -> productMiniDtoTransformer.transform(product))
                .orElseThrow(() -> new RuntimeException("No product found with this id."));
    }

    public ProductMiniDto createProduct(ProductMiniDto productMiniDto) {
        Product product = productTransformer.transform(productMiniDto);
        product.setFilename(
                fileUploaderService.upload(
                        product.getFilename()));

        product = productRepository.save(product);

        return productMiniDtoTransformer.transform(product);
    }

    public void deleteProduct(int id) {
        productRepository.deleteById(id);
    }

    public void modify(ProductMiniDto productMiniDto) {
        Optional<Product> product = productRepository.findById(productMiniDto.getId());

        if (product.isPresent()) {
            product.get().setDescription(productMiniDto.getDescription());
            product.get().setPrice(productMiniDto.getPrice());
            product.get().setName(productMiniDto.getName());
            product.get().setQuantity(productMiniDto.getQuantity());

            if (productMiniDto.getFilename() != null && !productMiniDto.getFilename().isEmpty()) {
                product.get().setFilename(
                        fileUploaderService.upload(
                                productMiniDto.getFilename()));
            }

            productRepository.save(product.get());
        }
    }
}
