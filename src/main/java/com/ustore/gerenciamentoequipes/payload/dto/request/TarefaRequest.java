package com.ustore.gerenciamentoequipes.payload.dto.request;

import com.ustore.gerenciamentoequipes.enums.Prioridade;
import com.ustore.gerenciamentoequipes.enums.StatusTarefa;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;
import java.util.UUID;

public record TarefaRequest(
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @NotBlank(message = "A descrição é obrigatória")
        @Size(max = 500, message = "A descrição deve ter no máximo 500 caracteres")
        String descricao,

        @NotNull(message = "O status é obrigatório")
        StatusTarefa status,

        @NotNull(message = "A prioridade é obrigatória")
        Prioridade prioridade,

        @FutureOrPresent(message = "A data de entrega deve ser no futuro ou hoje")
        LocalDateTime dataEntrega,

        @Size(max = 100, message = "O nome do projeto deve ter no máximo 100 caracteres")
        String projeto,

        String tags,

        @NotNull(message = "É necessário atribuir um responsável")
        UUID responsavelId
) {
}
