package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.entities.ShoppingCart;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.repositories.ShoppingCartRepository;
import com.kissolga.webshop.security.dtos.authentication.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

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
}
