package com.leandromendes25.Api_de_eventos.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    PARTICIPANT("Participant"), ORGANIZER("Organizer");

private String description;

    Role(String description) {
        this.description = description;
    }
    @JsonValue
    public String getDescricao() {
        return description;
    }
    @JsonCreator
    public static Role fromDescricao(String description) {
        for (Role role : Role.values()) {
            if (role.getDescricao().equalsIgnoreCase(description)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Role: " + description);
    }
}
