package com.wilker.bff_agendador_tarefas_api.infrastructure.exceptions;

public class ResponseParsingException extends RuntimeException {
    public ResponseParsingException(String message) {
        super(message);
    }
}
