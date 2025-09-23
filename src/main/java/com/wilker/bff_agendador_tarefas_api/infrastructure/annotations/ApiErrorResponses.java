package com.wilker.bff_agendador_tarefas_api.infrastructure.annotations;

import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.ErrorDTOResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(
                responseCode = "400",
                description = "Requisição inválida",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorDTOResponse.class))
        ),
        @ApiResponse(
                responseCode = "401",
                description = "Não autorizado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorDTOResponse.class))
        ),
        @ApiResponse(
                responseCode = "404",
                description = "Não encontrado",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorDTOResponse.class))
        ),
        @ApiResponse(
                responseCode = "409",
                description = "Conflito",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorDTOResponse.class))
        ),
        @ApiResponse(
                responseCode = "500",
                description = "Erro interno",
                content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = ErrorDTOResponse.class))
        )
})
public @interface ApiErrorResponses {
}

