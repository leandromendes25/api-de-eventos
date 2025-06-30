package com.leandromendes25.Api_de_eventos.controller;

import com.leandromendes25.Api_de_eventos.dto.UserModelRequestDTO;
import com.leandromendes25.Api_de_eventos.dto.UserModelResponseDTO;
import com.leandromendes25.Api_de_eventos.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
@Tag(name = "Usuários", description = "Gerenciamento de usuários: criação, listagem, atualização e remoção.")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    @Operation(
            summary = "Cadastrar usuário",
            description = """
            Cria um novo usuário na plataforma com nome, e-mail e tipo de função (Organizador ou Participante).
            
            - O e-mail deve ter um formato válido.
            - A função deve ser informada como `Organizer` ou `Participant`.
            """
    )
    public ResponseEntity<UserModelResponseDTO> create(@Valid @RequestBody UserModelRequestDTO user) {
        var userModel = service.createNewUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
    }

    @GetMapping
    @Operation(
            summary = "Listar usuários",
            description = "Retorna uma lista com todos os usuários cadastrados no sistema, independentemente da função."
    )
    public ResponseEntity<List<UserModelResponseDTO>> findAll() {
        return ResponseEntity.ok().body(service.findAll());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar usuário",
            description = """
            Atualiza completamente os dados de um usuário com base no seu ID.
            É necessário fornecer todos os atributos novamente, mesmo que apenas um vá ser alterado.
            """
    )
    public ResponseEntity<UserModelResponseDTO> update(@PathVariable UUID id, @RequestBody UserModelRequestDTO user) {
        UserModelResponseDTO updated = service.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.CREATED).body(updated);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir usuário",
            description = "Remove um usuário do sistema com base no seu ID. A exclusão é permanente."
    )
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }
}
