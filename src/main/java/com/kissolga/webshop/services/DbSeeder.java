package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.entities.Role;
import com.kissolga.webshop.repositories.RoleRepository;
import com.kissolga.webshop.repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbSeeder {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void seedDb() {
        if (roleRepository.findByName("ROLE_CUSTOMER").isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_CUSTOMER");
            roleRepository.save(role);
        }
    }
}

