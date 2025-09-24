package com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class TelefoneDTORequest {

    private String ddd;
    private String numero;


}
