package com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class EnderecoDTORequest {

    private String rua;
    private String numero;
    private String complemento;
    private String estado;
    private String cidade;
    private String cep;
}
