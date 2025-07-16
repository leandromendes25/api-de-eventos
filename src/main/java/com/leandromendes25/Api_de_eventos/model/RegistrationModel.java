package com.leandromendes25.Api_de_eventos.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "tb_registration")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class RegistrationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;
    @ManyToOne
    @JoinColumn(name = "id_event", insertable = false, updatable = false)
    private EventModel event;
    @Column(name = "id_event")
    private UUID eventId;
    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private UserModel user;
    @Column(name = "id_user")
    private UUID userId;
    @CreationTimestamp
    private LocalDateTime registrationDate;
}
