package com.example.ProyectoPostgres.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("API ejercicio papeleria")
                        .version("1.0")
                        .description("Api´s de ejercicio Papeleria")
                        .contact(new Contact()
                                .name("Rumi Torres")
                                .email("rbtorres@ucundinamarca.edu.co")));
    }
}
