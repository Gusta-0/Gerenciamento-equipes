package com.ustore.gerenciamentoequipes.config;

import com.ustore.gerenciamentoequipes.payload.dto.request.LoginRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Tag(name = "Autenticação", description = "Endpoints para login e recuperação de senha")
public interface AuthAPI {

    @Operation(
            summary = "Login do usuário",
            description = "Autentica o usuário com email e senha e retorna um token JWT em formato de String"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(mediaType = "text/plain", schema = @Schema(type = "string", example = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    String login(
            @Parameter(description = "Dados de login do usuário", required = true)
            @Valid @RequestBody LoginRequest request
    );


    @Operation(
            summary = "Recuperar senha",
            description = "Envia instruções de recuperação de senha para o e-mail do usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "E-mail de recuperação enviado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Void> recuperarSenha(
            @Parameter(description = "E-mail do usuário", required = true)
            @RequestParam String email
    );

    @Operation(
            summary = "Redefinir senha",
            description = "Permite redefinir a senha do usuário através de um token de recuperação"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Senha redefinida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Token inválido ou expirado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<Void> redefinirSenha(
            @Parameter(description = "E-mail do usuário", required = true)
            @RequestParam String email,

            @Parameter(description = "Nova senha do usuário", required = true)
            @RequestParam String novaSenha
    );
}

