package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.exceptions.OrganizerFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.RoleUnfitException;
import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.enums.Role;
import com.leandromendes25.Api_de_eventos.repository.EventRepository;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository evtRepo;

    @Autowired
    private UserRepository usrRepo;

    public EventModel createNewEvent(EventModel evt){
    var organizer = usrRepo.findById(evt.getOrganizerUserId()).orElseThrow(() -> new OrganizerFoundException("Organizer not found"));
    if(organizer.getRole() != Role.ORGANIZER){
        throw new RoleUnfitException("Apenas organizadores podem criar eventos");
    }
    EventModel event = new EventModel();
    event.setTitle(evt.getTitle());
    event.setLocal(evt.getLocal());
    event.setDescription(evt.getDescription());
    event.setEventDate(evt.getEventDate());
    event.setOrganizerUser(organizer);
    event.setOrganizerUserId(organizer.getId());
    return evtRepo.save(event);
    }
    public List<EventModel> listAll(){
        return evtRepo.findAll();
    }
}
