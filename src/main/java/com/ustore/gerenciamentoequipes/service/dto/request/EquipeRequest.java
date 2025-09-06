package com.ustore.gerenciamentoequipes.service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.UUID;

public record EquipeRequest(
        @NotBlank(message = "O nome é obrigatório!")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotEmpty(message = "A equipe deve ter pelo menos 1 membro")
        List<UUID> usuariosIds
) {
}
