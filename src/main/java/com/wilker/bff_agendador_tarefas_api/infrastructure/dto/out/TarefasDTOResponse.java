package com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wilker.bff_agendador_tarefas_api.infrastructure.enums.StatusNotificacaoEnum;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TarefasDTOResponse {

    private String id;
    private String nomeTarefa;
    private String descricao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    private StatusNotificacaoEnum statusNotificacaoEnum;

    @Override
    public String toString() {
        return "TarefasDTOResponse{" +
                "id='" + id + '\'' +
                ", nomeTarefa='" + nomeTarefa + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataCriacao=" + dataCriacao +
                ", dataEvento=" + dataEvento +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", dataAlteracao=" + dataAlteracao +
                ", statusNotificacaoEnum=" + statusNotificacaoEnum +
                '}';
    }
}
