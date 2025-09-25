package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;

import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nomeCompleto,
        String email,
        NivelAcesso nivelAcesso,
        Cargo cargo,
        String telefone,
        StatusUser statusUser
) {
}
