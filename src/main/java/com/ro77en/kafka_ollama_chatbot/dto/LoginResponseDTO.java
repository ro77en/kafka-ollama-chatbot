package com.ro77en.kafka_ollama_chatbot.dto;

import java.util.List;

public record LoginResponseDTO(
        String token,
        UserInfo userInfo) {
    public record UserInfo(String username, List<String> roles) {}
}
