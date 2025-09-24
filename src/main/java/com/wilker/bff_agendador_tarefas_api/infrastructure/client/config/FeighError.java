package com.wilker.bff_agendador_tarefas_api.infrastructure.client.config;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.ErrorDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.BussinessException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.ConflictException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.IllegalArgumentException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.ResourceNotFoundException;
import com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Slf4j
public class FeighError implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        String mensagem = mensagemErro(response);

        switch (response.status()) {
            case 409:
                return new ConflictException(mensagem);
            case 404:
                return new ResourceNotFoundException(mensagem);
            case 401:
                return new UnauthorizedException(mensagem);
            case 400:
                return new IllegalArgumentException(mensagem);
            default:
                return new BussinessException("Erro inesperado: " + mensagem);
        }
    }

    private String mensagemErro(Response response) {
        try {
            if (Objects.isNull(response.body())) {
                return "";
            }

            String json = new String(response.body().asInputStream().readAllBytes(), StandardCharsets.UTF_8);
            ObjectMapper mapper = new ObjectMapper();
            ErrorDTOResponse error = mapper.readValue(json, ErrorDTOResponse.class);
            return error.getMensagem(); // pega só a mensagem
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}