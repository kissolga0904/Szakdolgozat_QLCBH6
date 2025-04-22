package com.kissolga.webshop.repositories;

import com.kissolga.webshop.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByOrderByIdDesc();
}
