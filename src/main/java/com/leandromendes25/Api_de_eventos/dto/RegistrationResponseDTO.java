package com.leandromendes25.Api_de_eventos.dto;

import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.UserModel;

public record RegistrationResponseDTO (EventModel evt, UserModel user) {
}