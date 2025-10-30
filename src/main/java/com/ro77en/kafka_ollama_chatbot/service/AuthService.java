package com.ro77en.kafka_ollama_chatbot.service;

import com.ro77en.kafka_ollama_chatbot.dto.LoginRequestDTO;
import com.ro77en.kafka_ollama_chatbot.dto.LoginResponseDTO;
import com.ro77en.kafka_ollama_chatbot.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    private final JwtService jwtService;
    private final AuthenticationManager authManager;

    public AuthService(JwtService jwtService, AuthenticationManager authManager) {
        this.jwtService = jwtService;
        this.authManager = authManager;
    }

    public LoginResponseDTO authenticate(LoginRequestDTO loginDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(
                loginDTO.username(),
                loginDTO.password()
        );

        Authentication auth = authManager.authenticate(authToken);

        String token = jwtService.generateToken(auth);
        String username = auth.getName();

        // TODO: add roles table to database
        List<String> roles = auth.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        var userInfo = new LoginResponseDTO.UserInfo(username, roles);

        return new LoginResponseDTO(token, userInfo);
    }
}

