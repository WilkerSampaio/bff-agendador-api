package com.wilker.bff_agendador_tarefas_api.controller;

import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.EnderecoDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.LoginDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.TelefoneDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.in.UsuarioDTORequest;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.EnderecoDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.TelefoneDTOResponse;
import com.wilker.bff_agendador_tarefas_api.infrastructure.dto.out.UsuarioDTOResponse;
import com.wilker.bff_agendador_tarefas_api.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
    @ApiResponse(responseCode = "200", description = "Usuário salvo com sucesso")
    @ApiResponse(responseCode =  "409", description = "Usuário já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public ResponseEntity<UsuarioDTOResponse> registraUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest) {
        return ResponseEntity.ok(usuarioService.salvarUsuario(usuarioDTORequest));
    }

    @PostMapping("/login")
    @Operation(summary = "Login Usuário", description = "Authentica usuário")
    @ApiResponse(responseCode = "200", description = "Usuário logado")
    @ApiResponse(responseCode =  "401", description = "Credencias inválidas")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    public String authenticarUsuario(@RequestBody LoginDTORequest loginDTORequest){
        return usuarioService.authenticarUsuario(loginDTORequest);
    }

    @GetMapping
    @Operation(summary = "Buscar Dados do Usuário por Email", description = "Buscar dados do usuário por email ")
    @ApiResponse(responseCode = "200", description = "Usuário encontrado")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<UsuarioDTOResponse> buscarUsuarioPeloEmail(@RequestParam ("email") String email,
                                                                     @RequestHeader(value = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPeloEmail(email, token));
    }

    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta um Usuário", description = "Deleta um usuário por email")
    @ApiResponse(responseCode = "200", description = "Usuário deletado com sucesso")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<Void> deletaUsuarioPeloEmail(@PathVariable String email,
                                                       @RequestHeader (name = "Authorization", required = false) String token){
        usuarioService.deletaUsuarioPeloEmail(email,token);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    @Operation(summary = "Atualiza Dados do Usuário", description = "Atualiza dados de um usuário")
    @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<UsuarioDTOResponse> atualizarDadosUsuario(@RequestBody UsuarioDTORequest usuarioDTORequest,
                                                                    @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarDadosUsuario(usuarioDTORequest, token));
    }

    @PutMapping("/endereco")
    @Operation(summary = "Atualiza Endereço do Usuário", description = "Atualiza um endereço do usuário")
    @ApiResponse(responseCode = "200", description = "Endereço atualizado com sucesso")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @ApiResponse(responseCode =  "409", description = "Endereço já cadastrado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<EnderecoDTOResponse> atualizarEndereco(@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                 @RequestParam("id") Long id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarEndereco(enderecoDTORequest,id, token));
    }

    @PutMapping("/telefone")
    @Operation(summary = "Atualiza Telefone do Usuário", description = "Atualiza um telefone do usuário")
    @ApiResponse(responseCode = "200", description = "telefone atualizado com sucesso")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @ApiResponse(responseCode =  "409", description = "Telefone já cadastrado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<TelefoneDTOResponse> atualizarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                 @RequestParam ("id") Long id,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.atualizarTelefone(telefoneDTORequest, id ,token));
    }

    @PostMapping("/endereco")
    @Operation(summary = "Salva Novo Endereço do Usuário", description = "Salva endereço de usuário")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @ApiResponse(responseCode =  "409", description = "Endereço já cadastrado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<EnderecoDTOResponse> cadastrarEndereco (@RequestBody EnderecoDTORequest enderecoDTORequest,
                                                                  @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastrarEndereco(enderecoDTORequest,token));
    }

    @PostMapping("/telefone")
    @Operation(summary = "Salva Novo Telefone do Usuário", description = "Salva telefone de usuário")
    @ApiResponse(responseCode = "200", description = "telefone salvo com sucesso")
    @ApiResponse(responseCode =  "404", description = "Usuário não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro de servidor")
    @ApiResponse(responseCode = "401", description = "Usuário não autenticado")
    @ApiResponse(responseCode =  "409", description = "Telefone já cadastrado")
    @SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
    public ResponseEntity<TelefoneDTOResponse> cadastrarTelefone(@RequestBody TelefoneDTORequest telefoneDTORequest,
                                                                 @RequestHeader (name = "Authorization", required = false) String token){
        return ResponseEntity.ok(usuarioService.cadastrarTelefone(telefoneDTORequest,token));
    }
}
