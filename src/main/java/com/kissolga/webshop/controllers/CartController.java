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
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CartController {
    private final CartService cartService;

    @GetMapping("/get")
    public List<CartProductDto> getCart(HttpServletRequest request) {
        return cartService.getCart(request);
    }

    @PostMapping("/add-product/{productId}/quantity/{quantity}")
    public List<CartProductDto> addProductToCart(HttpServletRequest request, @PathVariable Integer productId, @PathVariable Integer quantity) {
        return cartService.addProductToCart(request, productId, quantity);
    }

    @DeleteMapping("/delete-product/{productId}")
    public List<CartProductDto> deleteProductFromCart(HttpServletRequest request, @PathVariable Integer productId) {
        return cartService.deleteProductFromCart(request, productId);
    }

    @PutMapping("/modify-product/{productId}/quantity/{quantity}")
    public List<CartProductDto> modifyProductQuantity(HttpServletRequest request, @PathVariable Integer productId, @PathVariable Integer quantity) {
        return cartService.modifyProductQuantity(request, productId, quantity);
    }

    @PostMapping("/clear")
    public void clearCart(HttpServletRequest request) {
        ShoppingCart cart = cartService.getOrCreateShoppingCart(request);
        cartService.clearCart(cart);
    }
}
