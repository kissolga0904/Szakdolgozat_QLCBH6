package com.kissolga.webshop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/webshop")
    public String home() {
        return "fasz";
    }
}
