package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.user.UserRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.user.UserResponseDTO;
import com.leandromendes25.Api_de_eventos.exceptions.UserFoundException;
import com.leandromendes25.Api_de_eventos.exceptions.UserNotFoundException;
import com.leandromendes25.Api_de_eventos.model.UserModel;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserResponseDTO createNewUser(UserRequestDTO user) {
        userRepo.findByEmail(user.email().toLowerCase()).ifPresent((email) -> {
            throw new UserFoundException("User already registered");
        });
        UserModel userModel = new UserModel();
        userModel.setName(user.name());
        userModel.setEmail(user.email().toLowerCase());
        userModel.setRole(user.role());
        var password = passwordEncoder.encode(user.password());
        userModel.setPassword(password);
        var userFound = userRepo.save(userModel);
        return new UserResponseDTO(userFound.getId(),userFound.getName(),userFound.getEmail(),userFound.getRole(),userFound.getCreatedAt());
    }

    public List<UserResponseDTO> findAll() {
        return userRepo.findAll().stream().map(user -> new UserResponseDTO(user.getId(),user.getName(),user.getEmail(),user.getRole(),user.getCreatedAt())).toList();
    }

    public UserResponseDTO updateUser(UUID user, UserRequestDTO usr) {
        var userFound = userRepo.findById(user).orElseThrow(() -> new UserNotFoundException("User not found"));
        userFound.setName(usr.name());
        userFound.setEmail(usr.email().toLowerCase());
        userFound.setPassword(usr.password());
        userFound.setRole(usr.role());
        userRepo.save(userFound);
        return new UserResponseDTO(userFound.getId(), userFound.getName(), userFound.getEmail(), userFound.getRole(),userFound.getCreatedAt());
    }

    public void deleteUserById(UUID userId) {
        var user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepo.deleteById(user.getId());
    }
}
