package com.wilker.bff_agendador_tarefas_api.infrastructure.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeighConfig {

    @Bean
    public FeighError feighError(){
        return new FeighError();
    }
}
