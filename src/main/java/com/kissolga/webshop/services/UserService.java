package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Role;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.entity.UserTransformer;
import com.kissolga.webshop.domain.transformers.minidto.UserMiniDtoTransformer;
import com.kissolga.webshop.repositories.RoleRepository;
import com.kissolga.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserTransformer userTransformer;
    private final UserMiniDtoTransformer userMiniDtoTransformer;
    private final PasswordEncoder passwordEncoder;

    public void registration(UserMiniDto user) {
        Role role = roleRepository.findByName("ROLE_CUSTOMER")
                .orElseThrow(() -> new RuntimeException("No customer role found"));

        User userEntity = userTransformer.transform(user);
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);
    }

    public List<UserMiniDto> users() {
        return userMiniDtoTransformer.transform(userRepository.findAll());
    }

    public void addNewAdmin(UserMiniDto user) {
        Role role = roleRepository.findByName("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("No customer role found"));

        User userEntity = userTransformer.transform(user);
        userEntity.setRole(role);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        userRepository.save(userEntity);
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}

