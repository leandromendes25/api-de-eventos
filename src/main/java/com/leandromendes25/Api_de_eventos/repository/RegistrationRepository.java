package com.leandromendes25.Api_de_eventos.repository;

import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.RegistrationModel;
import com.leandromendes25.Api_de_eventos.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface RegistrationRepository extends JpaRepository<RegistrationModel, UUID> {
    boolean existsByUserAndEvent(UserModel user, EventModel event);
    List<RegistrationModel> findAllByEvent(EventModel event);
}