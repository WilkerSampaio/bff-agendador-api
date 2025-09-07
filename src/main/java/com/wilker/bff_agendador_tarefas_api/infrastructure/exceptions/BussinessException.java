package com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions;

public class BussinessException extends RuntimeException {
    public BussinessException(String message) {
        super(message);
    }
}
