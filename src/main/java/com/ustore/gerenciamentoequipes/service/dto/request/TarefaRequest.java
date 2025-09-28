package com.ustore.gerenciamentoequipes.service.dto.request;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

public record TarefaRequest(
        @NotBlank(message = "O título é obrigatório")
        @Size(max = 150, message = "O título deve ter no máximo 150 caracteres")
        String titulo,

        @Size(max = 500, message = "O comentário deve ter no máximo 500 caracteres")
        String comentario,

        @NotBlank(message = "O status é obrigatório")
        @Pattern(regexp = "A FAZER | EM PROGRESSO | REVISÃO | CONCLUÍDA", message = "Status inválido")
        StatusTarefa status,

        @NotBlank(message = "A prioridade é obrigatória")
        @Pattern(regexp = "BAIXA|MEDIA|ALTA|URGENTE", message = "Prioridade inválida")
        Prioridade prioridade,

        @FutureOrPresent(message = "A data de entrega deve ser no futuro ou hoje")
        LocalDateTime dataEntrega,

        @Size(max = 100, message = "O nome do projeto deve ter no máximo 100 caracteres")
        String projeto,

        String tags,

        @NotNull(message = "É necessário atribuir pelo menos um responsável")
        Set<UUID> responsaveisIds
) {
}
