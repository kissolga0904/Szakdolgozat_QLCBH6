package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.OrderStatusMiniDto;
import com.kissolga.webshop.domain.transformers.dto.OrderDtoTransformer;
import com.kissolga.webshop.domain.transformers.minidto.OrderStatusMiniDtoTransformer;
import com.kissolga.webshop.repositories.OrderStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderStatusService {
    private final OrderStatusRepository orderStatusRepository;

    private final OrderStatusMiniDtoTransformer orderStatusMiniDtoTransformer;

    public List<OrderStatusMiniDto> getAll(){
        return orderStatusMiniDtoTransformer.transform(orderStatusRepository.findAll());
    }
}
