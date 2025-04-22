package com.kissolga.webshop.services;

import com.kissolga.webshop.domain.entities.*;
import com.kissolga.webshop.repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DbSeeder {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedDb() {
        seedRoles();
        seedUser();
        seedProducts();
        seedOrderStatus();
        seedShippingMethod();
        seedPaymentMethods();
    }

    private void seedPaymentMethods() {
        if (paymentMethodRepository.findFirstByName("Card").isEmpty()) {
            PaymentMethod paymentMethod1 = new PaymentMethod();
            paymentMethod1.setName("Card");

            PaymentMethod paymentMethod2 = new PaymentMethod();
            paymentMethod2.setName("Cash on delivery");

            paymentMethodRepository.save(paymentMethod1);
            paymentMethodRepository.save(paymentMethod2);

        }
    }

    private void seedShippingMethod() {
        if (shippingMethodRepository.findFirstByName("Normal shipping").isEmpty()) {
            ShippingMethod newShippingMethod1 = new ShippingMethod();
            newShippingMethod1.setName("Normal shipping");
            newShippingMethod1.setPrice(8);

            ShippingMethod newShippingMethod2 = new ShippingMethod();
            newShippingMethod2.setName("Fast shipping");
            newShippingMethod2.setPrice(15);

            shippingMethodRepository.save(newShippingMethod1);
            shippingMethodRepository.save(newShippingMethod2);
        }

    }

    private void seedRoles() {
        if (roleRepository.findByName("ROLE_CUSTOMER").isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_CUSTOMER");
            roleRepository.save(role);
        }

        if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
        }
    }

    private void seedUser() {
        if (userRepository.findByUsername("testadmin").isEmpty()) {
            User user = new User();
            user.setUsername("testadmin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setRole(roleRepository.findByName("ROLE_ADMIN").get());

            User user2 = new User();
            user2.setUsername("testcustomer");
            user2.setPassword(passwordEncoder.encode("1234"));
            user2.setRole(roleRepository.findByName("ROLE_CUSTOMER").get());

            userRepository.save(user);
            userRepository.save(user2);
        }

    }

    private void seedOrderStatus() {
        if (orderStatusRepository.findFirstByStatus("IN_PROCESS").isEmpty()) {
            OrderStatus newStatus = new OrderStatus();

            newStatus.setStatus("IN_PROCESS");

            orderStatusRepository.save(newStatus);
        }
        if (orderStatusRepository.findFirstByStatus("PREPARING_FOR_DELIVERY").isEmpty()) {
            OrderStatus newStatus = new OrderStatus();

            newStatus.setStatus("PREPARING_FOR_DELIVERY");

            orderStatusRepository.save(newStatus);
        }
        if (orderStatusRepository.findFirstByStatus("DELIVERED").isEmpty()) {
            OrderStatus newStatus = new OrderStatus();

            newStatus.setStatus("DELIVERED");

            orderStatusRepository.save(newStatus);
        }
    }

    private void seedProducts() {
        if (productRepository.findByName("Sakura").isEmpty()) {
            Product product = new Product();
            product.setName("Sakura");
            product.setPrice(19);
            product.setDescription("Inspired by nature's delicate beauty");
            product.setFilename("sakura.jpg");
            product.setQuantity(10);

            productRepository.save(product);
        }

    }
}
