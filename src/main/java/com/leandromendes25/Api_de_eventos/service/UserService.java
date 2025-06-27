package com.leandromendes25.Api_de_eventos.service;

import com.leandromendes25.Api_de_eventos.dto.UserRequestDTO;
import com.leandromendes25.Api_de_eventos.exceptions.UserFoundException;
import com.leandromendes25.Api_de_eventos.model.UserModel;
import com.leandromendes25.Api_de_eventos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public UserModel createNewUser(UserRequestDTO user) {
        userRepo.findByEmail(user.email().toLowerCase()).ifPresent((email) -> {
            throw new UserFoundException("Usuario já cadastrado");
        });
        UserModel userModel = new UserModel();
        userModel.setName(user.name());
        userModel.setEmail(user.email());
        userModel.setRole(user.role());

        return userRepo.save(userModel);
    }
    public List<UserModel> findAll(){
        return userRepo.findAll();
    }
}
