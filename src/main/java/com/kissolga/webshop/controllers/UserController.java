package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Role;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.entity.UserTransformer;
import com.kissolga.webshop.domain.transformers.minidto.UserMiniDtoTransformer;
import com.kissolga.webshop.repositories.RoleRepository;
import com.kissolga.webshop.repositories.UserRepository;
import com.kissolga.webshop.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public void registration(@RequestBody UserMiniDto user) {
        userService.registration(user);
    }

    @GetMapping("/users")
    public List<UserMiniDto> users() {
        return userService.users();
    }

    @PostMapping("/add")
    public void addNewAdmin(@RequestBody UserMiniDto user) {
        userService.addNewAdmin(user);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}

