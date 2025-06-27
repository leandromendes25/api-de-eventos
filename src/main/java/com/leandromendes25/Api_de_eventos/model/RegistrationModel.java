package com.leandromendes25.Api_de_eventos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_registration")
@Data
@NoArgsConstructor
public class RegistrationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_event")
    private EventModel event;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private UserModel user;
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
