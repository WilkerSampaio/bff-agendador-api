package com.wilker.bff_agendador_tarefas_api.controller;


import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.TarefasDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TarefasDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.enums.StatusNotificacaoEnum;
import com.wilker.bff_agendador_tarefas_api.service.TarefasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastrar tarefas do usuário")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    @Operation(summary = "Criar Tarefa de Usuário", description = "Cadastra uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salva com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @ApiResponse(responseCode =  "409", description = "Tarefa já cadastrada")
    public ResponseEntity<TarefasDTOResponse> registraTarefa(@RequestBody TarefasDTORequest tarefasDTORequest,
                                                             @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.salvarTarefa(tarefasDTORequest, token));
    }

    @GetMapping("/eventos")
    @Operation(summary = "Busca Todas as Tarefas por Periodo", description = "Busca tarefas cadastradas por periodo")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "404", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaListaDeTarefaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal,
            @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.buscaListaDeTarefaPorPeriodo(dataInicial, dataFinal, token));
    }

    @GetMapping
    @Operation(summary = "Busca Lista de Tarefas por Email do Usuário", description = "Busca tarefas pelo email")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "404", description = "Tarefas não encontradas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefaPorEmail(@RequestHeader (name = "Authorization", required = false)String token){
        return ResponseEntity.ok(tarefasService.buscarTarefaPorEmail(token));
    }

    @DeleteMapping
    @Operation(summary = "Deleta Tarefa por ID", description = "Deleta tarefa cadastrada pelo ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletada com sucesso")
    @ApiResponse(responseCode =  "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    public ResponseEntity<Void> deletaTarefaPorId(@RequestParam ("id") String id,
                                                  @RequestHeader (name = "Authorization", required = false) String token){
           tarefasService.deletaTarefaPorId(id, token);
           return ResponseEntity.ok().build();
    }

    @PatchMapping
    @Operation(summary = "Altera Status da Tarefa", description = "Altera status da tarefa cadastrada")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    public ResponseEntity<TarefasDTOResponse> alterarStatusTarefa(@RequestParam ("status") StatusNotificacaoEnum status,
                                                                  @RequestParam ("id") String id,
                                                                  @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alteraStatusTarefa(status, id, token));
    }

    @PutMapping
    @Operation(summary = "Altera Dados da Tarefa", description = "Altera dados da tarefa cadastrada")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    public ResponseEntity<TarefasDTOResponse> alterarDadosTarefa(@RequestBody TarefasDTORequest tarefasDTORequest,
                                                                 @RequestParam ("id") String id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(tarefasService.alterarDadosTarefa(tarefasDTORequest, id, token));
    }
}
