package com.wilker.bff_agendador_tarefas_api.service;


import com.wilker.bff_agendador_tarefas_api.infrastructure.client.UsuarioClient;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.EnderecoDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.LoginDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.TelefoneDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.UsuarioDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.EnderecoDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TelefoneDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.UsuarioDTOResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UsuarioService {

    private final UsuarioClient usuarioClient;

    public UsuarioDTOResponse salvarUsuario(UsuarioDTORequest usuarioDTORequest){
        return usuarioClient.registraUsuario(usuarioDTORequest);
    }

    public String authenticarUsuario(LoginDTORequest loginDTORequest){
        return usuarioClient.authenticarUsuario(loginDTORequest);
    }

    public UsuarioDTOResponse buscarUsuarioPeloEmail(String email, String token){
        return usuarioClient.buscarUsuarioPeloEmail(email, token);
    }

    public void deletaUsuarioPeloEmail(String email, String token){
        usuarioClient.deletaUsuarioPeloEmail(email, token);
    }

    public UsuarioDTOResponse atualizarDadosUsuario(UsuarioDTORequest usuarioDTORequest, String token){
        return usuarioClient.atualizarDadosUsuario(usuarioDTORequest, token);
    }

    public EnderecoDTOResponse atualizarEndereco(EnderecoDTORequest enderecoDTORequest, Long idEndereco, String token){
        return usuarioClient.atualizarEndereco(enderecoDTORequest, idEndereco, token);
    }

    public TelefoneDTOResponse atualizarTelefone(TelefoneDTORequest telefoneDTORequest, Long idTelefone, String token){
        return usuarioClient.atualizarTelefone(telefoneDTORequest, idTelefone, token);

    }
    public EnderecoDTOResponse cadastrarEndereco(EnderecoDTORequest enderecoDTORequest, String token){
        return usuarioClient.cadastrarEndereco(enderecoDTORequest, token);
    }

    public TelefoneDTOResponse cadastrarTelefone(TelefoneDTORequest telefoneDTORequest, String token){
        return usuarioClient.cadastrarTelefone(telefoneDTORequest, token);
    }




}
