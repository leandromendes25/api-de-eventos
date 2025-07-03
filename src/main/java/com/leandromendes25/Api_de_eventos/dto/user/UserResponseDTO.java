package com.leandromendes25.Api_de_eventos.dto.user;

import com.leandromendes25.Api_de_eventos.model.enums.Role;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserResponseDTO(UUID id, String name, String email, Role role, LocalDateTime createdAt) {
}
