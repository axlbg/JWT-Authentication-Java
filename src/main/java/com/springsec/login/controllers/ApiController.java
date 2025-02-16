package com.springsec.login.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController  {
    @GetMapping("/public")
    public String publicEndpoint() {
        return "Hello this is a public endpoint";
    }


    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Hello this is a protected endpoint";
    }

}
