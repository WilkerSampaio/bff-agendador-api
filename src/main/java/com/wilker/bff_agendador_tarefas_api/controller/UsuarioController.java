package com.wilker.bff_agendador_tarefas_api.controller;

import com.wilker.bff_agendador_tarefas_api.infrastructure.annotations.ApiErrorResponses;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.EnderecoDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.LoginDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.TelefoneDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.UsuarioDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.EnderecoDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TelefoneDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.UsuarioDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.ViaCepDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.security.SecurityConfig;
import com.wilker.bff_agendador_tarefas_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuario")
@RestController
@RequiredArgsConstructor
@Tag(name = "Usuário", description = "Cadastro e Login de Usuários")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @Operation(summary = "Salva Usuários", description = "Cria um novo usuário")
    @ApiErrorResponses

    public ResponseEntity<UsuarioDTOResponse> registraUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Authentica usuário")
    @ApiErrorResponses
    public String authenticarUsuario(@RequestBody LoginDTORequest loginDTORequest){
        return usuarioService.authenticarUsuario(loginDTORequest);
    }

    @GetMapping
    @Operation(summary = "Buscar Dados do Usuário por Email", description = "Buscar dados do usuário por email ")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPeloEmail(@RequestParam ("email") String email,
                                                                     @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPeloEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta um Usuário", description = "Deleta um usuário por email")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<Void> deletaUsuarioPeloEmail(@PathVariable String email,
                                                       @RequestHeader (name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPeloEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza Dados do Usuário", description = "Atualiza dados de um usuário")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest,
                                                                    @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(usuarioDTORequest, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço do Usuário", description = "Atualiza um endereço do usuário")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(enderecoDTORequest,id, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone do Usuário", description = "Atualiza um telefone do usuário")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                 @RequestParam ("id") Long id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(telefoneDTORequest, id ,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Novo Endereço do Usuário", description = "Salva endereço de usuário")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                  @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(enderecoDTORequest,token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Novo Telefone do Usuário", description = "Salva telefone de usuário")
    @ApiErrorResponses
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<TelefoneDTOResponse> cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(telefoneDTORequest,token));
    }

    @GetMapping("/endereco/{cep}")
    @Operation(summary = "Busca Endereço pelo CEP", description = "Busca dados de endereço recebendo um CEP")
    public ResponseEntity<ViaCepDTOResponse> buscarEndereco(@PathVariable("cep") String cep){
        return ResponseEntity.ok(usuarioService.buscarEnderecoPorCep(cep));
    }

}
