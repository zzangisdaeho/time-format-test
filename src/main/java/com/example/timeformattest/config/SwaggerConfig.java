package com.example.timeformattest.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Pageable;

import java.time.LocalTime;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI docswaveOpenAPI(){
        SpringDocUtils.getConfig()
                .replaceWithClass(LocalTime.class, String.class);
        return new OpenAPI()
                .components(new Components())
                .info(getOpenApiInfo());
    }

    private Info getOpenApiInfo() {
        return new Info().title("Docswave V3 Api Server");
    }
}
