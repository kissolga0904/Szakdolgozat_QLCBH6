package com.kissolga.webshop.security.service;

import com.kissolga.webshop.security.dtos.authentication.SecurityUser;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultUserDetailsService implements UserDetailsService {

    public final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(usernameOrEmail);

        if (user.isEmpty()) {
            user = userRepository.findByEmail(usernameOrEmail);

            if (user.isEmpty()) {
                throw new UsernameNotFoundException("User not found with this username or email.");
            }

        }

        return new SecurityUser(user.get());
    }
}

