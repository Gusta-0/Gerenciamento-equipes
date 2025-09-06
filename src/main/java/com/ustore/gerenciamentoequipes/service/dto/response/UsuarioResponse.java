package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.enums.RoleType;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        RoleType role,
        String telefone,
        UUID equipeId
) {
}
