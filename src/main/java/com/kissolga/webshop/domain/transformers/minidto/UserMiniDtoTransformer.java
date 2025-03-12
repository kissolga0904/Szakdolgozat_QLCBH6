package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMiniDtoTransformer {
    public UserMiniDto transform(User user) {
        return UserMiniDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
    }

    public List<UserMiniDto> transform(List<User> users) {
        return users.stream()
                .map(u -> transform(u))
                .collect(Collectors.toList());
    }
}
