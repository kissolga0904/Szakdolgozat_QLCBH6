package com.kissolga.webshop.domain.transformers.entity;

import com.kissolga.webshop.domain.dtos.UserMiniDto;
import com.kissolga.webshop.domain.entities.User;
import org.springframework.stereotype.Controller;

@Controller
public class UserTransformer {
    public User transform(UserMiniDto userMiniDto) {
        User user = new User();

        user.setId(userMiniDto.getId());
        user.setEmail(userMiniDto.getEmail());
        user.setPassword(userMiniDto.getPassword());
        user.setUsername(userMiniDto.getUsername());

        return user;
    }
}
