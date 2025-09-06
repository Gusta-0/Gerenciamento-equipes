package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TarefaResponse(
        UUID id,
        String titulo,
        String comentario,
        LocalDateTime dataCriacao,
        LocalDate prazo,
        Prioridade prioridade,
        StatusTarefa status,
        UUID equipeId,
        UUID usuarioId
) {
}
