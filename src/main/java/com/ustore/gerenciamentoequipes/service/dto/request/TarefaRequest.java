package com.ustore.gerenciamentoequipes.service.dto.request;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record TarefaRequest(
        @NotBlank(message = "O título é obrigatório")
        @Size(min = 3, max = 100, message = "O título deve ter entre 3 e 100 caracteres")
        String titulo,
        String comentario,
        LocalDateTime dataCriacao,
        LocalDate prazo,
        Prioridade prioridade,
        StatusTarefa status,
        @NotNull(message = "O ID da equipe é obrigatório")
        UUID equipeId,
        @NotNull(message = "O ID do usuário responsável é obrigatório")
        UUID usuarioId
) {
}
