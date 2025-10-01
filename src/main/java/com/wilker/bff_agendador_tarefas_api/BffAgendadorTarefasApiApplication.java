package com.wilker.bff_agendador_tarefas_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
public class BffAgendadorTarefasApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BffAgendadorTarefasApiApplication.class, args);
    }

}