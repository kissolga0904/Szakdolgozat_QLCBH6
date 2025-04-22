package com.kissolga.webshop.domain.transformers.dto;

import com.kissolga.webshop.domain.dtos.OrderDto;
import com.kissolga.webshop.domain.entities.Order;
import com.kissolga.webshop.domain.transformers.minidto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoTransformer {

    @Autowired
    private OrderStatusMiniDtoTransformer orderStatusMiniDtoTransformer;
    @Autowired
    private ShippingMethodMiniDtoTransformer shippingMethodMiniDtoTransformer;
    @Autowired
    private PaymentMethodMiniDtoTransformer paymentMethodMiniDtoTransformer;
    @Autowired
    private AddressMiniDtoTransformer addressMiniDtoTransformer;
    @Autowired
    private ShoppingCartMiniDtoTransformer shoppingCartMiniDtoTransformer;

    public OrderDto transform(Order order) {
        OrderDto result = new OrderDto();

        result.setId(order.getId());
        result.setOrderDate(order.getOrderDate());
        result.setStatus(orderStatusMiniDtoTransformer.transform(order.getStatus()));
        result.setShippingMethod(shippingMethodMiniDtoTransformer.transform(order.getShippingMethod()));
        result.setPaymentMethod(paymentMethodMiniDtoTransformer.transform(order.getPaymentMethod()));
        result.setAddress(addressMiniDtoTransformer.transform(order.getAddress()));
        result.setShoppingCart(shoppingCartMiniDtoTransformer.transform(order.getShoppingCart()));

        return result;
    }

    public List<OrderDto> transform(List<Order> orders) {
        return orders.stream()
                .map(this::transform)
                .collect(Collectors.toList());
    }
}
