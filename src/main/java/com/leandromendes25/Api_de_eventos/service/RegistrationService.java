package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.registration.RegistrationRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.registration.RegistrationResponseDTO;
import com.leandromendes25.Api_de_eventos.exceptions.AllReadyRegisteredException;
import com.leandromendes25.Api_de_eventos.exceptions.EventNotFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.RoleUnfitException;
import com.leandromendes25.Api_de_eventos.exceptions.UserNotFoundException;
import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.model.RegistrationModel;
import com.leandromendes25.Api_de_eventos.model.enums.Role;
import com.leandromendes25.Api_de_eventos.repository.EventRepository;
import com.leandromendes25.Api_de_eventos.repository.RegistrationRepository;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository usrRepo;
    @Autowired
    private EventRepository evtRepo;
    @Autowired
    private RegistrationRepository regisRepo;

    public RegistrationResponseDTO createNewRegistration(RegistrationRequestDTO registration, UUID eventId) {
        var event = evtRepo.findById(eventId).orElseThrow(() -> new EventNotFoundException("Event not found"));
        var usr = usrRepo.findById(registration.userId()).orElseThrow(() -> new UserNotFoundException("User not found"));

        if (regisRepo.existsByUserAndEvent(usr, event)) {
            throw new AllReadyRegisteredException("User already registered");
        }
        if (usr.getRole() != Role.PARTICIPANT) {
            throw new RoleUnfitException("Only participants can participate");
        }
       var result = RegistrationModel.builder().event(event).user(usr).build();
        regisRepo.save(result);
        return new RegistrationResponseDTO(event,usr);
    }

    public List<RegistrationResponseDTO> findAll(UUID eventId) {
        EventModel evt = new EventModel();
        evt.setId(eventId);
        return regisRepo.findAllByEvent(evt).stream()
                .map(reg -> new RegistrationResponseDTO(reg.getEvent(), reg.getUser()))
                .collect(Collectors.toList());
    }
}