package com.kissolga.webshop.security.dtos;

import java.util.List;

public record JwtResponse(String jwt, String userName, List<String> roles) {
}
