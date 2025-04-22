package com.kissolga.webshop.security.configs;

import com.kissolga.webshop.security.service.AuthEntryPointJwt;
import com.kissolga.webshop.security.service.AuthTokenFilter;
import com.kissolga.webshop.security.service.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthEntryPointJwt unauthorizedHandler;

    @Autowired
    private JwtUtils jwtUtils;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("api/user/**").permitAll()
                                .requestMatchers("h2-console/**").permitAll()
                                .requestMatchers("api/auth/authenticate").permitAll()
                                .requestMatchers("/api/auth/authenticate").permitAll()
                                .requestMatchers("/api/products").permitAll()
                                .requestMatchers("/api/products/get-all").permitAll()
                                .requestMatchers("api/products/find-by-id/{id}").permitAll()
                                .requestMatchers("/api/cart/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/api/shipping-methods/**").permitAll()
                                .requestMatchers("/api/order-status/**").permitAll()

                                .requestMatchers("/api/products/create").hasRole("ADMIN")
                                .requestMatchers("/api/products/modify").hasRole("ADMIN")
                                .requestMatchers("/api/products/delete/**").hasRole("ADMIN")
                                .requestMatchers("/api/user/add").hasRole("ADMIN")
                                .requestMatchers("/api/user/delete/{id}").hasRole("ADMIN")
                                .requestMatchers("/api/order/change-status/{oid}/status/{sid}").hasRole("ADMIN")
                                .requestMatchers("/api/order/delete/{oid}").hasRole("ADMIN")



                                .anyRequest().authenticated()


                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthTokenFilter authenticationJwtTokenFilter() {
        return new AuthTokenFilter(jwtUtils, userDetailsService);
    }

    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(
                Arrays.asList("http://localhost:4200", "https://localhost:4200", "localhost:4200", "localhost"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST", "PUT", "DELETE"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
