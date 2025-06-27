package com.leandromendes25.Api_de_eventos.repository;

import com.leandromendes25.Api_de_eventos.model.EventModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventModel, UUID> {
}
