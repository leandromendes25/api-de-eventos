package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.UserModelRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.UserModelResponseDTO;
import com.leandromendes25.Api_de_eventos.exceptions.UserFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.UserNotFoundException;
import com.leandromendes25.Api_de_eventos.model.UserModel;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserModelResponseDTO createNewUser(UserModelRequestDTO user) {
        userRepo.findByEmail(user.email().toLowerCase()).ifPresent((email) -> {
            throw new UserFoundException("User already registered");
        });
        UserModel userModel = new UserModel();
        userModel.setName(user.name());
        userModel.setEmail(user.email());
        userModel.setRole(user.role());
        var userFound = userRepo.save(userModel);
        return new UserModelResponseDTO(userFound.getId(),userFound.getName(),userFound.getEmail(),userFound.getRole(),userFound.getCreatedAt());
    }

    public List<UserModelResponseDTO> findAll() {
        return userRepo.findAll().stream().map(user -> new UserModelResponseDTO(user.getId(),user.getName(),user.getEmail(),user.getRole(),user.getCreatedAt())).toList();
    }

    public UserModelResponseDTO updateUser(UUID user, UserModelRequestDTO usr) {
        var userFound = userRepo.findById(user).orElseThrow(() -> new UserNotFoundException("User not found"));
        userFound.setName(usr.name());
        userFound.setEmail(usr.email().toLowerCase());
        userRepo.save(userFound);
        return new UserModelResponseDTO(userFound.getId(), userFound.getName(), userFound.getEmail(), userFound.getRole(),userFound.getCreatedAt());
    }

    public void deleteUserById(UUID userId) {
        var user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepo.deleteById(user.getId());
    }
}
