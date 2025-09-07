package com.wilker.bff_agendador_tarefas_api.infrastructure.client;


import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.TarefasDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TarefasDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.enums.StatusNotificacaoEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "tarefa", url = "${tarefa.url}")
public interface TarefaClient {

    @PostMapping
    TarefasDTOResponse registraTarefa(@RequestBody TarefasDTORequest tarefasDTORequest,
                                      @RequestHeader("Authorization") String token);
    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscaListaDeTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    @GetMapping
    List<TarefasDTOResponse> buscaTarefaPorEmail(@RequestHeader ("Authorization") String token);

    @DeleteMapping
     void deletaTarefaPorId(@RequestParam ("id") String id, @RequestHeader("Authorization") String token);

    @PatchMapping
    TarefasDTOResponse alterarStatusTarefa(@RequestParam ("status") StatusNotificacaoEnum status,
                                           @RequestParam ("id") String id, @RequestHeader("Authorization") String token);
    @PutMapping
    TarefasDTOResponse alterarDadosTarefa(@RequestBody TarefasDTORequest tarefasDTORequest,
                                          @RequestParam ("id") String id, @RequestHeader("Authorization") String token);
}
