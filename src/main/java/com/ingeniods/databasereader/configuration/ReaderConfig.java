package com.ingeniods.databasereader.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingeniods.databasereader.application.GenerateSchema;
import com.ingeniods.databasereader.domain.service.EsquemaService;
import com.ingeniods.databasereader.domain.service.TablaStorageService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ReaderConfig {


    @Bean
    public GenerateSchema applicationService(EsquemaService esquemaService,
                                             TablaStorageService tablaStorageService) {
        return new GenerateSchema(esquemaService, tablaStorageService);
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
