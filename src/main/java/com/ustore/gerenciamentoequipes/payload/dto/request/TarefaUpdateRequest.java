package com.ustore.gerenciamentoequipes.payload.dto.request;

import com.ustore.gerenciamentoequipes.enums.Prioridade;
import com.ustore.gerenciamentoequipes.enums.StatusTarefa;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record TarefaUpdateRequest(
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        StatusTarefa status,

        Prioridade prioridade,

        @FutureOrPresent(message = "A data de entrega deve ser no futuro ou hoje")
        LocalDateTime dataEntrega,

        @Size(max = 100, message = "O nome do projeto deve ter no máximo 100 caracteres")
        String projeto,

        String tags
) {
}