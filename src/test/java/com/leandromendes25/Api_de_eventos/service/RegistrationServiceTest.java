package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.registration.RegistrationRequestDTO;
import com.leandromendes25.Api_de_eventos.exceptions.EventNotFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.UserNotFoundException;
import com.leandromendes25.Api_de_eventos.model.EventModel;
import com.leandromendes25.Api_de_eventos.repository.EventRepository;
import com.leandromendes25.Api_de_eventos.repository.RegistrationRepository;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class RegistrationServiceTest {
    @InjectMocks
    private RegistrationService service;

    @Mock
    private UserRepository usrRepo;
    @Mock
    private EventRepository evtRepo;
    @Mock
    private RegistrationRepository regisRepo;

    @Test
    @DisplayName("Should not be able to register with event not found")
    public void should_not_be_able_to_register_with_event_not_found(){
        try{
        service.createNewRegistration(null,null);
        }catch (Exception e){
        assertThat(e).isInstanceOf(EventNotFoundException.class);
        }
    }
    @Test
    public void shoud_not_be_able_to_register_with_user_not_found(){
        var idEvent = UUID.randomUUID();
        var idRegistration = UUID.randomUUID();
        var event = new EventModel();
        var registration = new RegistrationRequestDTO(idRegistration);
        event.setId(idEvent);
        when(evtRepo.findById(idEvent)).thenReturn(Optional.of(event));

        try{
            service.createNewRegistration(registration,idEvent);
        }catch (Exception e){
            assertThat(e).isInstanceOf(UserNotFoundException.class);
        }
    }
}
