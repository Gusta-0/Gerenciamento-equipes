package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;

import java.time.LocalDateTime;
import java.util.UUID;

public record TarefaResponse(
        UUID id,
        String titulo,
        String descricao,
        String criador,
        StatusTarefa status,
        Prioridade prioridade,
        LocalDateTime dataEntrega,
        String projeto,
        String tags,
        LocalDateTime dataCriacao,
        UsuarioResumoResponse responsavel
) {
}
