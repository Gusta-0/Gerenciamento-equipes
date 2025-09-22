package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nome,
        String sobrenome,
        String cpf,
        String email,
        Cargo role,
        String telefone,
        com.ustore.gerenciamentoequipes.infrastructure.entity.Cargo cargo
) {
}
