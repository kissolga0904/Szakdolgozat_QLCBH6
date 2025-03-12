package com.kissolga.webshop.domain.transformers.dto;

import com.kissolga.webshop.domain.dtos.RoleDto;
import com.kissolga.webshop.domain.entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoTransformer {
    public RoleDto transform(Role role) {
        return RoleDto.builder()
                .id(role.getId())
                .name(role.getName())
                .build();
    }
}
