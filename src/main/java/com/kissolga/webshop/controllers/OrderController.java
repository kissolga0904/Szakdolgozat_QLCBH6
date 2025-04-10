package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.request.PlaceOrderRequest;
import com.kissolga.webshop.domain.entities.Address;
import com.kissolga.webshop.domain.entities.Order;
import com.kissolga.webshop.domain.entities.OrderStatus;
import com.kissolga.webshop.domain.entities.ShoppingCart;
import com.kissolga.webshop.repositories.OrderRepository;
import com.kissolga.webshop.repositories.OrderStatusRepository;
import com.kissolga.webshop.repositories.PaymentMethodRepository;
import com.kissolga.webshop.repositories.ShippingMethodRepository;
import com.kissolga.webshop.security.dtos.authentication.SecurityUser;
import com.kissolga.webshop.services.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private CartService cartService;

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @PostMapping("/place")
    public void placeOrder(HttpServletRequest request, @RequestBody PlaceOrderRequest placeOrderRequest) {
        ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
        Optional<OrderStatus> inProcessStatus = orderStatusRepository.findFirstByStatus("IN_PROCESS");

        Address address = new Address();
        address.setCity(placeOrderRequest.getCity());
        address.setCountry(placeOrderRequest.getCountry());
        address.setStreet(placeOrderRequest.getStreet());
        address.setHouseNumber(placeOrderRequest.getHouseNumber());

        Order order = new Order();
        order.setOrderDate(new Date());
        order.setShoppingCart(shoppingCart);
        order.setStatus(inProcessStatus.get());
        order.setShippingMethod(shippingMethodRepository.getReferenceById(placeOrderRequest.getShippingMethodId()));
        order.setPaymentMethod(paymentMethodRepository.getReferenceById(placeOrderRequest.getPaymentMethodId()));
        order.setAddress(address);

        orderRepository.save(order);
        cartService.createNewCartFromRequest(request);
    }
}
