package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record TarefaResponse(
        UUID id,
        String titulo,
        String comentario,
        StatusTarefa status,
        Prioridade prioridade,
        String projeto,
        String tags,
        LocalDateTime dataCriacao,
        LocalDateTime dataEntrega,
        Set<UsuarioResponse> responsaveis
) {
}
