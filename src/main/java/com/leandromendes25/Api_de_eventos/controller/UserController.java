package com.leandromendes25.Api_de_eventos.controller;

import com.leandromendes25.Api_de_eventos.dto.UserRequestDTO;
import com.leandromendes25.Api_de_eventos.model.UserModel;
import com.leandromendes25.Api_de_eventos.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserRequestDTO user) {
        var userModel = service.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }
}
