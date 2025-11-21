package com.codeup.InMemory_HU2.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("In Memory Catalog of Events and Places - HU2")
                        .version("2.0.0")
                        .description("""
                                API REST para gestionar eventos y venues con las siguientes características:
                                
                                Persistencia con Spring Data JPA + H2, Validaciones completas con mensajes descriptivos, Paginación y filtros (ciudad, categoría, fecha), Manejo global de errores (400, 404, 409), Documentación interactiva con Swagger
                                """)
                        .contact(new Contact()
                                .name("Melanie Rivera")
                                .url("https://github.com/RiveraMg"))
                )
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de desarrollo")
                ));
    }
}