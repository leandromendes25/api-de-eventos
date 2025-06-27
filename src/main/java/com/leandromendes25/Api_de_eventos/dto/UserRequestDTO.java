package com.leandromendes25.Api_de_eventos.dto;

import com.leandromendes25.Api_de_eventos.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserRequestDTO(@NotBlank(message = "the field cannot be empty") String name,
                             @Email(message = "the email field must be valid") String email, Role role) {
}
