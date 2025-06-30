package com.leandromendes25.Api_de_eventos.dto;

import com.leandromendes25.Api_de_eventos.model.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserModelResponseDTO(UUID id, String name, String email, Role role, LocalDateTime createdAt) {
}
