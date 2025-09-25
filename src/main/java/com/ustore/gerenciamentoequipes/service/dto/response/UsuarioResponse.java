package com.ustore.gerenciamentoequipes.service.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;

import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponse(
        UUID id,
        String nomeCompleto,
        String email,
        NivelAcesso nivelAcesso,
        Cargo cargo,
        String telefone,
        StatusUser statusUser,
        String imagem,

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime dataCadastro
) {
}
