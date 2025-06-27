package com.leandromendes25.Api_de_eventos.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    PARTICIPANT("Participant"), ORGANIZER("Organizer");

private String descricao;

    Role(String descricao) {
        this.descricao = descricao;
    }
    @JsonValue
    public String getDescricao() {
        return descricao;
    }
    @JsonCreator
    public static Role fromDescricao(String descricao) {
        for (Role role : Role.values()) {
            if (role.getDescricao().equalsIgnoreCase(descricao)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Valor inválido para Role: " + descricao);
    }
}
