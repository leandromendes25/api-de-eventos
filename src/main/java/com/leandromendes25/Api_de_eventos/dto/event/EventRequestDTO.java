package com.leandromendes25.Api_de_eventos.dto.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record EventRequestDTO(String title, String description, LocalDateTime eventDate, String local, UUID organizerUserId) {
}
