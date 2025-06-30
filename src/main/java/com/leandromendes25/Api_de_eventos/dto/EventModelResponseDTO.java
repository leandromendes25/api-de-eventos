package com.leandromendes25.Api_de_eventos.dto;

import com.leandromendes25.Api_de_eventos.model.UserModel;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventModelResponseDTO(UUID eventId, String title, LocalDateTime eventDate, String local, UUID idOrganizer, UserModel organizer) {
}
