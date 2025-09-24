package com.wilker.bff_agendador_tarefas_api.infrastructure.client;


import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.EnderecoDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.LoginDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.TelefoneDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.UsuarioDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.EnderecoDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TelefoneDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.UsuarioDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.ViaCepDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {

    @PostMapping
    UsuarioDTOResponse registraUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest);

    @PostMapping("/login")
    String authenticarUsuario(@RequestBody LoginDTORequest loginDTORequest);

    @GetMapping
    UsuarioDTOResponse buscarUsuarioPeloEmail(@RequestParam("email") String email,
                                              @RequestHeader("Authorization") String token);

    @DeleteMapping("/{email}")
    void deletaUsuarioPeloEmail(@PathVariable String email,
                                                       @RequestHeader("Authorization") String token);

    @PutMapping
    UsuarioDTOResponse atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest,
                                             @RequestHeader("Authorization") String token);

    @PutMapping("/endereco")
    EnderecoDTOResponse atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                          @RequestParam("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PutMapping("/telefone")
    TelefoneDTOResponse atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                          @RequestParam ("id") Long id,
                                          @RequestHeader("Authorization") String token);

    @PostMapping("/endereco")
    EnderecoDTOResponse cadastrarEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest,
                                           @RequestHeader ("Authorization") String token);

    @PostMapping("/telefone")
    TelefoneDTOResponse cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                          @RequestHeader ("Authorization") String token);

    @GetMapping("/endereco/{cep}")
    ViaCepDTOResponse buscarDadosCep (@PathVariable ("cep") String cep);

}
