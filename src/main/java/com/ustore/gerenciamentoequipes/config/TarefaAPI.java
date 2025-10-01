package com.ustore.gerenciamentoequipes.config;

import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaFiltroRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaUpdateRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.TarefaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@Tag(name = "Tarefas", description = "Gerenciamento de tarefas")
public interface TarefaAPI {

    @Operation(
            summary = "Criar nova tarefa",
            description = "Cria uma nova tarefa vinculada a um responsável e um criador"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa criada com sucesso",
                    content = @Content(schema = @Schema(implementation = TarefaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Usuário responsável não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<TarefaResponse> criarTarefa(@Valid @RequestBody TarefaRequest tarefaRequest);

    @Operation(
            summary = "Filtrar tarefas",
            description = "Retorna uma lista paginada de tarefas de acordo com os filtros informados. "
                    + "Todos os parâmetros são opcionais e podem ser combinados. \n\n"
                    + "- **nomeIntegrante**: pesquisa no nome do criador ou responsável da tarefa \n"
                    + "- **status**: A_FAZER, EM_PROGRESSO, REVISAO, CONCLUIDA \n"
                    + "- **prioridade**: BAIXA, MEDIA, ALTA, URGENTE \n"
                    + "- **responsavelId**: ID do responsável vinculado à tarefa \n"
                    + "- **departamento**: Cargo/Departamento vinculado à tarefa \n"
                    + "- **projeto**: Nome (ou parte do nome) do projeto \n"
                    + "- **dataInicio / dataFim**: intervalo de datas de entrega \n"
                    + "- **somenteAtrasadas**: quando `true`, retorna apenas tarefas atrasadas"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas filtradas retornada com sucesso",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Page.class))),
            @ApiResponse(responseCode = "400", description = "Parâmetros de filtro inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno no servidor")
    })
    @GetMapping("/filtrar")
    ResponseEntity<Page<TarefaResponse>> filtrar(@ParameterObject @ModelAttribute TarefaFiltroRequest filtro,
                                                 @ParameterObject Pageable pageable);

    @Operation(
            summary = "Atualizar tarefa existente",
            description = "Atualiza os dados de uma tarefa existente, incluindo título, descrição, status e responsável"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso",
                    content = @Content(schema = @Schema(implementation = TarefaResponse.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Tarefa ou responsável não encontrado",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    ResponseEntity<TarefaResponse> atualizarTarefa(
            @PathVariable("id") UUID tarefaId,
            @Valid @RequestBody TarefaUpdateRequest updateRequest);

    @Operation(
            summary = "Deletar tarefa",
            description = "Remove uma tarefa existente pelo seu ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Tarefa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    ResponseEntity<Void> deletarTarefa(@PathVariable UUID id);
}

