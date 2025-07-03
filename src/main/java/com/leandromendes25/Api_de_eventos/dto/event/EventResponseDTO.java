package com.leandromendes25.Api_de_eventos.dto.event;

import com.leandromendes25.Api_de_eventos.model.UserModel;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventResponseDTO(UUID eventId, String title, LocalDateTime eventDate, String local, UUID idOrganizer, UserModel organizer) {
}
