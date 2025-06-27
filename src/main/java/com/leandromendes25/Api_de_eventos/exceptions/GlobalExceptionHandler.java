package com.leandromendes25.Api_de_eventos.exceptions;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(RoleUnfitException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(RoleUnfitException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(OrganizerFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(OrganizerFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(AllReadyRegisteredException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(AllReadyRegisteredException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(UserNotFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(EventNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserExists(EventNotFoundException ex) {
        Map<String, String> error = Map.of("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleDeserializationError(HttpMessageNotReadableException ex) {
        String message = "Erro ao processar a requisição. Verifique se o JSON está corretamente formatado.";

        // Enum inválido ou tipo incorreto
        if (ex.getCause() instanceof InvalidFormatException cause) {
            String campo = cause.getPath().get(0).getFieldName();
            Class<?> tipo = cause.getTargetType();

            if (tipo.isEnum()) {
                String valores = Arrays.stream(tipo.getEnumConstants())
                        .map(Object::toString)
                        .collect(Collectors.joining(", "));
                message = String.format("Valor inválido para o campo '%s'. Valores aceitos: %s", campo, valores);
            } else if (tipo.equals(Integer.class) || tipo.equals(int.class)) {
                message = String.format("O campo '%s' deve ser um número inteiro", campo);
            } else if (tipo.equals(LocalDate.class)) {
                message = String.format("O campo '%s' deve estar em formato de data (ex: yyyy-MM-dd)", campo);
            }
        }

        return ResponseEntity.badRequest().body(Map.of("erro", message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> erros = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .toList();

        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("status", HttpStatus.BAD_REQUEST.value());
        corpo.put("mensagem", "Erro de validação nos campos enviados.");
        corpo.put("erros", erros);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(corpo);
}
}