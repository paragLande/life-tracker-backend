package com.parag.lifetracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI lifeTrackerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Life Tracker API")
                        .description("REST API documentation for habits, daily logs, journal entries, statistics, and life compass reviews.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Life Tracker")
                                .email("support@lifetracker.local"))
                        .license(new License()
                                .name("Private Project")));
    }
}
