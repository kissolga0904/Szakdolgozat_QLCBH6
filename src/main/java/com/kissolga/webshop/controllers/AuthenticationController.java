package com.kissolga.webshop.controllers;

import com.kissolga.webshop.domain.dtos.request.AuthenticationRequest;
import com.kissolga.webshop.domain.dtos.response.AuthenticationResponse;
import com.kissolga.webshop.security.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        // AUTHENTIKÁLJUK A USERT
        // HIBAS CREDENTAILS ESETEN BadCredentailsException
        // HA A KOVETKEZO SOR LEMEGY SIKERESEN AKKOR TUDJUK HOGY AUTHENTIKALVA VAGYUNK
        Authentication authenticatedUser = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getUsername(), authenticationRequest.getPassword()));



        // UTIL SEGITSEGEVEL LEGENERALJUK A TOKENT
        final String jwt = jwtUtils.generateJwtToken(authenticatedUser);

        String role = userDetailsService.loadUserByUsername(authenticationRequest.getUsername())
                .getAuthorities()
                .stream()
                .findFirst()
                .get()
                .getAuthority();


        // TOKEN-T A RESPONSE-BAN VISSZAADJUK
        return ResponseEntity.ok(new AuthenticationResponse(jwt, role, authenticationRequest.getUsername()));
    }
}
