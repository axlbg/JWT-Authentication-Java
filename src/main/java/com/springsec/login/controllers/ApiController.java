package com.springsec.login.controllers;

import com.springsec.login.entity.UserEntity;
import com.springsec.login.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController  {
    @Autowired
    private AuthService authService;

    @GetMapping("/public")
    public String publicEndpoint() {
        return "Hello this is a public endpoint";
    }


    @GetMapping("/protected")
    public String protectedEndpoint() {
        return "Hello this is a protected endpoint";
    }


    @GetMapping("/me")
    public String getCurrentUser(@RequestHeader("Authorization") String token) {
        String jwtToken = token.replace("Bearer ", "");
        UserEntity user = authService.getUserFromToken(jwtToken);
        return "{ user: " +user.getUsername()+ " }";
    }
}
