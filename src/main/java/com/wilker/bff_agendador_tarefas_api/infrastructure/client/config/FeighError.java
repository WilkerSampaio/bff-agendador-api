package com.wilker.bff_agendador_tarefas_api.infrastructure.client.config;


import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.BussinessException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.ConflictException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.ResourceNotFoundException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class FeighError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String body = "";

        try {
            if (response.body() != null) {
                body = Util.toString(response.body().asReader());
            }
        } catch (IOException e) {
            log.error("Erro ao ler o corpo da resposta Feign", e);
        }

        log.error("Erro Feign - método: {}, status: {}", methodKey, response.status());

        switch (response.status()){
            case 409:
                return new ConflictException("Erro: 409, atributo já existente");

            case 404:
                return new ResourceNotFoundException("Erro: 404, recurso não encontrado");

            case 401:
                return new UnauthorizedException("Erro: 401, usuário não autorizado");

            default:
                return new BussinessException("Erro " + response.status() + ": Erro inesperado. Detalhes: " + body);
        }
    }
}
