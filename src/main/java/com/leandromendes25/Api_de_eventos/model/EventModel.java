package com.leandromendes25.Api_de_eventos.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;
@Entity(name = "tb_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class EventModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @EqualsAndHashCode.Include
    private UUID id;
    private String title;
    private String description;
    private LocalDateTime eventDate;
    private String local;
    @Column(name = "user_organizer_id")
    private UUID organizerUserId;
    @ManyToOne
    @JoinColumn(name = "user_organizer_id", insertable = false, updatable = false)
    private UserModel organizerUser;
}