package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.OrderStatusMiniDto;
import com.kissolga.webshop.services.OrderStatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order-status")
public class OrderStatusController {
    private final OrderStatusService orderStatusService;

    @GetMapping("/get-all")
    public List<OrderStatusMiniDto> getOrderStatuses() {
        return orderStatusService.getAll();
    }
}
