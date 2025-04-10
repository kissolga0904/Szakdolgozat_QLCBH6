package com.kissolga.webshop.repositories;

import com.kissolga.webshop.domain.entities.ShoppingCart;
import com.kissolga.webshop.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    Optional<ShoppingCart> findFirstByUserOrIpOrderByIdDesc(User user, String ip);
}
