package com.springsec.login.controllers;

import com.springsec.login.dto.LoginRequest;
import com.springsec.login.dto.SignUpRequest;
import com.springsec.login.entity.UserEntity;
import com.springsec.login.errorHandler.CustomAuthenticationException;
import com.springsec.login.services.AuthService;
import com.springsec.login.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        UserEntity user = authService.login(loginRequest.getUsername(), loginRequest.getPassword());
        return jwtService.generateToken(user.getUsername());
    }

    @PostMapping("/signup")
    public UserEntity signUp(@RequestBody SignUpRequest signUpRequest) {
        return authService.signUp(signUpRequest.getUsername(), signUpRequest.getPassword());
    }


}