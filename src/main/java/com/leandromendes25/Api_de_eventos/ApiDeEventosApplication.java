package com.leandromendes25.Api_de_eventos;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Registro de eventos", description = "API responsável pela gestão de eventos", version = "1"))
public class ApiDeEventosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDeEventosApplication.class, args);
	}
}