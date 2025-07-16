package com.leandromendes25.Api_de_eventos.controller;

import com.leandromendes25.Api_de_eventos.dto.user.AuthUserDTO;
import com.leandromendes25.Api_de_eventos.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthUserController {
    @Autowired
    private AuthUserService service;
    @PostMapping("/user")
    public ResponseEntity<Object> create(@RequestBody AuthUserDTO dto){
        try{
        var result = service.login(dto);
        return ResponseEntity.ok().body(result);
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
        }
    }
}
