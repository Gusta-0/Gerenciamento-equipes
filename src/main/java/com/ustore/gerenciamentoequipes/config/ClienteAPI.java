package com.ustore.gerenciamentoequipes.config;


import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioLogin;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioUpdateRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.UsuarioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Tag(name = "Colaboradores", description = "Gerenciamento de colaboradores")
public interface ClienteAPI {

    @Operation(summary = "Salvar novo colaborador",
            description = "Adiciona um novo colaborador na equipe")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador adicionado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> salvaUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest);

    @Operation(summary = "Login",
            description = "Autentica colaborador e retorna um token JWT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso",
                    content = @Content(schema = @Schema(example = "{ \"token\": \"jwt_token_aqui\" }"))),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    String login(@Valid @RequestBody UsuarioLogin dto);


    @Operation(summary = "Pesquisar colaboradores",
            description = "Pesquisa colaboradores por nome e/ou email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de colaboradores encontrada")
    })
    Page<UsuarioResponse> pesquisar(
            @Parameter(description = "Nome do colaborador (parcial)") @RequestParam(required = false) String nome,
            @Parameter(description = "Email do colaborador (parcial)") @RequestParam(required = false) String email,
            Pageable pageable
    );

    @Operation(summary = "Filtrar colaboradores",
            description = "Filtra colaboradores pelos enums Cargo, Status e Nível de Acesso")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de colaboradores encontrada")
    })
    Page<UsuarioResponse> filtrar(
            @Parameter(description = "Cargo do colaborador") @RequestParam(required = false) Cargo cargo,
            @Parameter(description = "Status do colaborador") @RequestParam(required = false) StatusUser status,
            @Parameter(description = "Nível de acesso do colaborador") @RequestParam(required = false) NivelAcesso nivel,
            Pageable pageable
    );

    @Operation(
            summary = "Atualizar dados do colaborador",
            description = "Atualiza os dados de um colaborador existente. " +
                    "Usuários comuns só podem alterar os próprios dados. " +
                    "Admins e Gerentes podem alterar qualquer colaborador."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Colaborador atualizado com sucesso",
                    content = @Content(schema = @Schema(implementation = UsuarioResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "403", description = "Sem permissão para atualizar este colaborador",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<UsuarioResponse> atualizaDadoUsuario(
            @Parameter(description = "ID do colaborador a ser atualizado", required = true)
            @PathVariable UUID id,

            @Valid @RequestBody UsuarioUpdateRequest dto
    ) throws AccessDeniedException;

    @Operation(
            summary = "Inativar colaborador",
            description = "Inativa um colaborador (delete lógico). " +
                    "Apenas Admins e Gerentes têm permissão para esta ação."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Colaborador inativado com sucesso"),
            @ApiResponse(responseCode = "403", description = "Sem permissão para inativar este colaborador",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Colaborador não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    void inativar(
            @Parameter(description = "ID do colaborador a ser inativado", required = true)
            @PathVariable UUID id
    ) throws AccessDeniedException;

}
