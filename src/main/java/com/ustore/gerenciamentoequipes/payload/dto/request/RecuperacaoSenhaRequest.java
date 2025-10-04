package com.ustore.gerenciamentoequipes.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RecuperacaoSenhaRequest(
        @NotBlank(message = "O email é obrigatório")
        @Email
        String email
) {}

