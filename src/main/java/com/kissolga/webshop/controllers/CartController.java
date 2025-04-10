package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.CartProductDto;
import com.kissolga.webshop.domain.entities.CartProduct;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.entities.ShoppingCart;
import com.kissolga.webshop.domain.transformers.dto.CartProductDtoTransformer;
import com.kissolga.webshop.repositories.CartProductRepository;
import com.kissolga.webshop.repositories.ProductRepository;
import com.kissolga.webshop.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CartProductDtoTransformer cartProductDtoTransformer;

    @GetMapping("/get")
    public List<CartProductDto> getCart(HttpServletRequest request) {
        ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);

        return cartProductDtoTransformer.transform(shoppingCart.getProducts());
    }

    @PostMapping("/add-product/{productId}/quantity/{quantity}")
    public List<CartProductDto> addProductToCart(HttpServletRequest request, @PathVariable Integer productId, @PathVariable Integer quantity) {
        ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
        Optional<Product> product = productRepository.findById(productId);

        if (product.isPresent()) {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setCart(shoppingCart);
            cartProduct.setProduct(product.get());
            cartProduct.setPrice(product.get().getPrice());
            cartProduct.setQuantity(quantity);

            cartProduct = cartProductRepository.save(cartProduct);
            shoppingCart.getProducts().add(cartProduct);
        }

        return cartProductDtoTransformer.transform(shoppingCart.getProducts());
    }

    @DeleteMapping("/delete-product/{productId}")
    public List<CartProductDto> deleteProductFromCart(HttpServletRequest request, @PathVariable Integer productId) {
        ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
        Optional<CartProduct> product = shoppingCart.getProducts()
                .stream()
                .filter(p -> p.getProduct().getId() == productId)
                .findFirst();

        if (product.isPresent()) {
            cartProductRepository.delete(product.get());

            shoppingCart.setProducts(shoppingCart.getProducts()
                    .stream()
                    .filter(p -> p.getProduct().getId() != productId)
                    .collect(Collectors.toList()));
        }

        return cartProductDtoTransformer.transform(shoppingCart.getProducts());
    }

    @PutMapping("/modify-product/{productId}/quantity/{quantity}")
    public List<CartProductDto> modifyProductQuantity(HttpServletRequest request, @PathVariable Integer productId, @PathVariable Integer quantity) {
        ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
        Optional<CartProduct> product = shoppingCart.getProducts()
                .stream()
                .filter(p -> p.getProduct().getId() == productId)
                .findFirst();

        if (product.isPresent()) {
            product.get().setQuantity(quantity);
            cartProductRepository.save(product.get());

            shoppingCart.setProducts(
                    shoppingCart.getProducts()
                            .stream()
                            .map(p -> {
                                if (p.getProduct().getId() == productId) {
                                    return product.get();
                                } else {
                                    return p;
                                }
                            })
                            .collect(Collectors.toList()));
        }

        return cartProductDtoTransformer.transform(shoppingCart.getProducts());
    }
}
