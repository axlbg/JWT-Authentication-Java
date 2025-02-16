package com.springsec.login.services;

import com.springsec.login.entity.UserEntity;
import com.springsec.login.errorHandler.CustomAuthenticationException;
import com.springsec.login.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    public UserEntity signUp(String username, String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new CustomAuthenticationException("User already exists.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password)); // Codificar la contraseña
        return userRepository.save(user);
    }

    public UserEntity login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomAuthenticationException("User not found"));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new CustomAuthenticationException("Incorrect password");
        }

        return user;
    }

    public UserEntity getUserFromToken(String token) {
        String username = jwtService.extractUser(token); // Extrae el usuario del token
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new CustomAuthenticationException("User not found"));
    }
}