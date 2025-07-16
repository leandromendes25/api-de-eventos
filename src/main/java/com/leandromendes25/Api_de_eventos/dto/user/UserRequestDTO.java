package com.leandromendes25.Api_de_eventos.dto.user;

import com.leandromendes25.Api_de_eventos.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRequestDTO(@NotBlank(message = "the field cannot be empty") String name,
                             @Email(message = "the email field must be valid") String email,
                             @Size(min = 8, message = "The minimum password strength must be 8 ")
                             @NotBlank String password, Role role) {
}