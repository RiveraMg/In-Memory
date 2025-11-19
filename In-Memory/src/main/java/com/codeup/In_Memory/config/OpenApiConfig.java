package com.codeup.In_Memory.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Catálogo In-Memory de Eventos y Lugares")
                        .version("1.0")
                        .description("API REST para gestionar eventos y sus lugares"));
    }
}
