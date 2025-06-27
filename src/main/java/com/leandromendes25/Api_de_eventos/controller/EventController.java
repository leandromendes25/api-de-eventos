package com.leandromendes25.Api_de_eventos.controller;

import com.leandromendes25.Api_de_eventos.dto.RegistrationRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.RegistrationResponseDTO;
import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.RegistrationModel;
import com.leandromendes25.Api_de_eventos.service.EventService;
import com.leandromendes25.Api_de_eventos.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<EventModel> create(@RequestBody EventModel evt) {
        var event = service.createNewEvent(evt);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @GetMapping
    public ResponseEntity<List<EventModel>> findAll() {
        return ResponseEntity.ok().body(service.listAll());
    }

    @PostMapping("{eventId}/registration")
    public ResponseEntity<RegistrationModel> createRegistration(@PathVariable UUID eventId, @RequestBody RegistrationRequestDTO registration) {
        var result = registrationService.createNewRegistration(registration, eventId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("{eventId}/registration")
    public ResponseEntity<List<RegistrationResponseDTO>> findAllRegistration(@PathVariable UUID eventId) {
        var result = registrationService.findAll(eventId);
        return ResponseEntity.ok().body(result);
    }
}