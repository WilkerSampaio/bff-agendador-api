package com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions;


public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
