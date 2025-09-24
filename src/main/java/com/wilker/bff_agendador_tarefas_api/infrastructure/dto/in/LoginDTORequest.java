package com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class LoginDTORequest {
    private String email;
    private String senha;
}
