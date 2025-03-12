package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Role;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.entity.UserTransformer;
import com.kissolga.webshop.domain.transformers.minidto.UserMiniDtoTransformer;
import com.kissolga.webshop.repositories.RoleRepository;
import com.kissolga.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserTransformer userTransformer;
    private final UserMiniDtoTransformer userMiniDtoTransformer;

    @PostMapping("/registration")
    public void registration(@RequestBody UserMiniDto user) {
        Role role = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("No customer role found"));

        User userEntity = userTransformer.transform(user);
        userEntity.setRole(role);

        userRepository.save(userEntity);
    }

    @GetMapping("/users")
    public List<UserMiniDto> users() {
        return userMiniDtoTransformer.transform(userRepository.findAll());
    }
}

