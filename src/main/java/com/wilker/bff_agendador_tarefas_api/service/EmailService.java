package com.wilker.bff_agendador_tarefas_api.service;


import com.wilker.bff_agendador_tarefas_api.infrastructure.client.EmailClient;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TarefasDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
    private final EmailClient emailClient;

    public void enviarEmail(TarefasDTOResponse tarefasDTOResponse) {
         emailClient.enviarEmail(tarefasDTOResponse);
    }
}


