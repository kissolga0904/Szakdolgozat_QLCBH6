package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.OrderDto;
import com.kissolga.webshop.domain.dtos.request.PlaceOrderRequest;
import com.kissolga.webshop.domain.entities.*;
import com.kissolga.webshop.domain.transformers.dto.OrderDtoTransformer;
import com.kissolga.webshop.repositories.*;
import com.kissolga.webshop.security.dtos.authentication.SecurityUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
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

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderDtoTransformer orderDtoTransformer;

    public void placeOrder(HttpServletRequest request, @RequestBody PlaceOrderRequest placeOrderRequest) {
        ShoppingCart shoppingCart = cartService.getOrCreateShoppingCart(request);
        shoppingCart.setUser(getLoggedInUser());

        Optional<OrderStatus> inProcessStatus = orderStatusRepository.findFirstByStatus("IN_PROCESS");

        Address address = new Address();
        address.setUser(getLoggedInUser());
        address.setCity(placeOrderRequest.getCity());
        address.setCountry(placeOrderRequest.getCountry());
        address.setStreet(placeOrderRequest.getStreet());
        address.setHouseNumber(placeOrderRequest.getHouseNumber());
        address.setPostalCode(placeOrderRequest.getPostalCode());

        addressRepository.save(address);


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


    public List<OrderDto> getAll() {
        return orderDtoTransformer.transform(orderRepository.findAllByOrderByIdDesc());
    }

    public OrderDto getById(@PathVariable int id) {
        Optional<Order> order = orderRepository.findById(id);

        if (order.isPresent()) {
            return orderDtoTransformer.transform(order.get());
        } else {
            return null;
        }
    }

    public void changeStatus(@PathVariable int oid, @PathVariable int sid) {
        Optional<Order> order = orderRepository.findById(oid);
        Optional<OrderStatus> status = orderStatusRepository.findById(sid);

        if (order.isPresent() && status.isPresent()) {
            order.get().setStatus(status.get());

            orderRepository.save(order.get());
        }
    }

    public void deleteOrder(@PathVariable int oid) {
        orderRepository.deleteById(oid);
    }

    private User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User loggedInUser;

        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getPrincipal())) {
            loggedInUser = ((SecurityUser) authentication.getPrincipal()).getUser();
        } else {
            loggedInUser = null;
        }

        return loggedInUser;
    }
}
