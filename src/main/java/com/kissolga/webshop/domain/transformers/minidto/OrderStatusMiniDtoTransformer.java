package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.OrderStatusMiniDto;
import com.kissolga.webshop.domain.entities.OrderStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderStatusMiniDtoTransformer {

    public OrderStatusMiniDto transform(OrderStatus orderStatus) {
        OrderStatusMiniDto result = new OrderStatusMiniDto();

        result.setId(orderStatus.getId());
        result.setStatus(orderStatus.getStatus());

        return result;
    }

    public List<OrderStatusMiniDto> transform(List<OrderStatus> statuses) {
        return statuses.stream().map(this::transform).collect(Collectors.toList());
    }
}
