package com.kissolga.webshop.repositories;

import com.kissolga.webshop.domain.entities.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Integer> {
    Optional<ShippingMethod> findFirstByName(String name);
}
