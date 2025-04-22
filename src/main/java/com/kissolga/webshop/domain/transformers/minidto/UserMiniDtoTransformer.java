package com.kissolga.webshop.domain.transformers.minidto;

import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.Role;
import com.kissolga.webshop.domain.entities.User;
import com.kissolga.webshop.domain.transformers.dto.RoleDtoTransformer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserMiniDtoTransformer {
    private final RoleDtoTransformer roleDtoTransformer;

    public UserMiniDto transform(User user) {
        return UserMiniDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .role(roleDtoTransformer.transform(user.getRole()))
                .build();
    }

    public List<UserMiniDto> transform(List<User> users) {
        return users.stream()
                .map(u -> transform(u))
                .collect(Collectors.toList());
    }
}
