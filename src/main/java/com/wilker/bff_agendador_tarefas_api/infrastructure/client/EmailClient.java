package com.wilker.bff_agendador_tarefas_api.infrastructure.client;


import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "notificacao", url ="${notificacao.url}")
public interface EmailClient {

    @PostMapping
     void enviarEmail(TarefasDTOResponse tarefasDTOResponse);
}
