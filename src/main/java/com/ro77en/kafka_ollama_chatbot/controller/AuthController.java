package com.ro77en.kafka_ollama_chatbot.controller;

import com.ro77en.kafka_ollama_chatbot.dto.LoginRequestDTO;
import com.ro77en.kafka_ollama_chatbot.dto.LoginResponseDTO;
import com.ro77en.kafka_ollama_chatbot.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<LoginResponseDTO> authenticate(@RequestBody LoginRequestDTO requestDTO) {
        LoginResponseDTO responseDTO = authService.authenticate(requestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(responseDTO);
    }
}
