package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.CartProductDto;
import com.kissolga.webshop.domain.entities.CartProduct;
import com.kissolga.webshop.domain.entities.Product;
import com.kissolga.webshop.domain.entities.ShoppingCart;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.dto.CartProductDtoTransformer;
import com.kissolga.webshop.repositories.CartProductRepository;
import com.kissolga.webshop.repositories.ProductRepository;
import com.kissolga.webshop.repositories.ShoppingCartRepository;
import com.kissolga.webshop.security.dtos.authentication.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartProductDtoTransformer cartProductDtoTransformer;



    public List<CartProductDto> getCart(HttpServletRequest request) {
        ShoppingCart shoppingCart = this.getOrCreateShoppingCart(request);

        return cartProductDtoTransformer.transform(shoppingCart.getProducts());
    }

    public List<CartProductDto> addProductToCart(HttpServletRequest request, @PathVariable Integer productId, @PathVariable Integer quantity) {
        ShoppingCart shoppingCart = this.getOrCreateShoppingCart(request);
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

    public List<CartProductDto> deleteProductFromCart(HttpServletRequest request, @PathVariable Integer productId) {
        ShoppingCart shoppingCart = this.getOrCreateShoppingCart(request);
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

    public List<CartProductDto> modifyProductQuantity(HttpServletRequest request, @PathVariable Integer productId, @PathVariable Integer quantity) {
        ShoppingCart shoppingCart = this.getOrCreateShoppingCart(request);
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

    public ShoppingCart getOrCreateShoppingCart(HttpServletRequest request) {
        User loggedInUser;
        String ip;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            loggedInUser = ((SecurityUser) authentication.getPrincipal()).getUser();
        } else {
            loggedInUser = null;
        }

        ip = getClientIp(request);

        Optional<ShoppingCart> shoppingCart = shoppingCartRepository.findFirstByUserOrIpOrderByIdDesc(loggedInUser, ip);

        if (shoppingCart.isEmpty()) {
            return createNewCart(ip, loggedInUser);
        }

        return shoppingCart.get();
    }

    public ShoppingCart createNewCartFromRequest(HttpServletRequest request) {
        User loggedInUser;
        String ip;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            loggedInUser = ((SecurityUser) authentication.getPrincipal()).getUser();
        } else {
            loggedInUser = null;
        }

        ip = getClientIp(request);

        return createNewCart(ip, loggedInUser);
    }

    private ShoppingCart createNewCart(String ip, User loggedInUser) {
        ShoppingCart newShoppingCart = new ShoppingCart();
        newShoppingCart.setIp(ip);
        newShoppingCart.setUser(loggedInUser);
        shoppingCartRepository.save(newShoppingCart);

        return newShoppingCart;
    }

    public String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    public void clearCart(ShoppingCart cart) {
        List<CartProduct> cartProducts = cart.getProducts();

        for (CartProduct cartProduct : cartProducts) {
            cartProduct.setCart(null); // break bidirectional link
            cartProductRepository.delete(cartProduct);
        }

        cartProducts.clear(); // clear list in memory too
    }
}
