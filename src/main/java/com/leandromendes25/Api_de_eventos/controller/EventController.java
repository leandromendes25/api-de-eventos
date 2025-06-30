package com.leandromendes25.Api_de_eventos.controller;

import com.leandromendes25.Api_de_eventos.dto.RegistrationRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.RegistrationResponseDTO;
import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.RegistrationModel;
import com.leandromendes25.Api_de_eventos.service.EventService;
import com.leandromendes25.Api_de_eventos.service.RegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("events")
@Tag(name = "Eventos", description = "Gerenciamento de eventos: criação, listagem e inscrições.")
public class EventController {

    @Autowired
    private EventService service;

    @Autowired
    private RegistrationService registrationService;

    @Operation(summary = "Criar evento", description = "Cadastra um novo evento informando título, descrição, data e local.")
    @PostMapping
    public ResponseEntity<EventModel> create(@RequestBody EventModel evt) {
        var event = service.createNewEvent(evt);
        return ResponseEntity.status(HttpStatus.CREATED).body(event);
    }

    @Operation(summary = "Listar eventos", description = "Retorna todos os eventos cadastrados no sistema.")
    @GetMapping
    public ResponseEntity<List<EventModel>> findAll() {
        return ResponseEntity.ok().body(service.listAll());
    }

    @Operation(
            summary = "Inscrever usuário",
            description = "Realiza a inscrição de um usuário participante em um evento específico. Basta informar o ID do evento e o ID do usuário."
    )
    @PostMapping("{eventId}/registration")
    public ResponseEntity<RegistrationModel> createRegistration(@PathVariable UUID eventId, @RequestBody RegistrationRequestDTO registration) {
        var result = registrationService.createNewRegistration(registration, eventId);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @Operation(
            summary = "Listar inscritos",
            description = "Retorna todos os usuários inscritos no evento informado pelo seu ID."
    )
    @GetMapping("{eventId}/registration")
    public ResponseEntity<List<RegistrationResponseDTO>> findAllRegistration(@PathVariable UUID eventId) {
        var result = registrationService.findAll(eventId);
        return ResponseEntity.ok().body(result);
    }
}
