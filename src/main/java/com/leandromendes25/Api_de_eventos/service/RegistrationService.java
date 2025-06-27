package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.RegistrationRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.RegistrationResponseDTO;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class RegistrationService {
    @Autowired
    private UserRepository usrRepo;
    @Autowired
    private EventRepository evtRepo;
    @Autowired
    private RegistrationRepository regisRepo;

    public RegistrationModel createNewRegistration(RegistrationRequestDTO registration, UUID eventId) {
        var event = evtRepo.findById(eventId).orElseThrow(() -> new EventNotFoundException("Evento não encontrado"));
        var usr = usrRepo.findById(registration.userId()).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));

        if (regisRepo.existsByUserAndEvent(usr, event)) {
            throw new AllReadyRegisteredException("Usuario já cadastrado");
        }
        if (usr.getRole() != Role.PARTICIPANT) {
            throw new RoleUnfitException("Apenas participantes podem participar");
        }
        RegistrationModel registrationModel = new RegistrationModel();
        registrationModel.setEvent(event);
        registrationModel.setUser(usr);
        return regisRepo.save(registrationModel);
    }

    public List<RegistrationResponseDTO> findAll(UUID eventId) {
        EventModel evt = new EventModel();
        evt.setId(eventId);
        return regisRepo.findAllByEvent(evt).stream()
                .map(reg -> new RegistrationResponseDTO(reg.getEvent(), reg.getUser()))
                .collect(Collectors.toList());
    }
}