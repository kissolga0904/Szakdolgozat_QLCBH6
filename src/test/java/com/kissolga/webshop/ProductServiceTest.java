package com.kissolga.webshop;

import com.kissolga.webshop.domain.dtos.ProductMiniDto;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.transformers.minidto.ProductMiniDtoTransformer;
import com.kissolga.webshop.repositories.ProductRepository;
import com.kissolga.webshop.services.ProductService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMiniDtoTransformer productMiniDtoTransformer;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findProductById_existingId_shouldReturnObject() {
        Product product = new Product();
        product.setId(5);

        ProductMiniDto productMiniDto = ProductMiniDto.builder()
                .id(5)
                .build();

        when(productRepository.findById(eq(5)))
                .thenReturn(Optional.of(product));
        when(productMiniDtoTransformer.transform(product)).thenReturn(productMiniDto);

        ProductMiniDto result = productService.findProductById(5);

        assertNotNull(result);
    }

    @Test
    public void findProductById_nonExistingId_shouldThrowException() {
        when(productRepository.findById(anyInt()))
                .thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.findProductById(5));
    }

}
