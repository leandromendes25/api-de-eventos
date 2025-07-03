package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.event.EventRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.event.EventResponseDTO;
import com.leandromendes25.Api_de_eventos.exceptions.EventNotFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.OrganizerFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.RoleUnfitException;
import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.enums.Role;
import com.leandromendes25.Api_de_eventos.repository.EventRepository;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService {

    @Autowired
    private EventRepository evtRepo;

    @Autowired
    private UserRepository usrRepo;

    public EventResponseDTO createNewEvent(EventRequestDTO evt){
    var organizer = usrRepo.findById(evt.organizerUserId()).orElseThrow(() -> new OrganizerFoundException("Organizer not found"));
    if(organizer.getRole() != Role.ORGANIZER){
        throw new RoleUnfitException("Apenas organizadores podem criar eventos");
    }
  var event =  EventModel.builder().title(evt.title())
            .local(evt.local()).description(evt.description())
            .eventDate(evt.eventDate()).organizerUser(organizer).organizerUserId(organizer.getId()).build();
      evtRepo.save(event);
     return new EventResponseDTO(event.getId(),event.getTitle(), event.getEventDate(),event.getLocal(),event.getOrganizerUserId(), event.getOrganizerUser());
    }
    public List<EventResponseDTO> listAll(){
         return evtRepo.findAll().stream().map(event ->
                 new EventResponseDTO(event.getId(),event.getTitle(),event.getEventDate(),
                         event.getLocal(),event.getOrganizerUserId(), event.getOrganizerUser())).toList();
    }
    public EventResponseDTO findById(UUID idEvent){
       var event = evtRepo.findById(idEvent).orElseThrow(() -> new EventNotFoundException("Evento não encontrado"));
        return new EventResponseDTO(
                event.getId(),event.getTitle(),event.getEventDate()
                ,event.getLocal(),event.getOrganizerUserId(),event.getOrganizerUser());
    }
}
