package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.OrderDto;
import com.kissolga.webshop.domain.dtos.request.PlaceOrderRequest;
import com.kissolga.webshop.domain.entities.*;
import com.kissolga.webshop.domain.transformers.dto.OrderDtoTransformer;
import com.kissolga.webshop.repositories.*;
import com.kissolga.webshop.security.dtos.authentication.SecurityUser;
import com.kissolga.webshop.services.CartService;
import com.kissolga.webshop.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/place")
    public void placeOrder(HttpServletRequest request, @RequestBody PlaceOrderRequest placeOrderRequest) {
        orderService.placeOrder(request, placeOrderRequest);
    }

    @GetMapping("/get-all")
    public List<OrderDto> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/get-by-id/{id}")
    public OrderDto getById(@PathVariable int id) {
        return orderService.getById(id);
    }

    @PostMapping("/change-status/{oid}/status/{sid}")
    public void changeStatus(@PathVariable int oid, @PathVariable int sid) {
        orderService.changeStatus(oid, sid);
    }

    @DeleteMapping("/delete/{oid}")
    public void deleteOrder(@PathVariable int oid) {
        orderService.deleteOrder(oid);
    }
}
