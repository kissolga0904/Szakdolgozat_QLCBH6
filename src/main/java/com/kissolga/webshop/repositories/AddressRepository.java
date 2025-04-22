package com.kissolga.webshop.repositories;

import com.kissolga.webshop.domain.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
