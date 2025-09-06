package com.ustore.gerenciamentoequipes.service.dto.response;

import java.time.LocalDateTime;
import java.util.UUID;

public record RecuperacaoSenhaResponse(
        UUID id,
        String token,
        LocalDateTime dataSolicitacao,
        LocalDateTime dataExpiracao,
        UUID usuarioId
) {}

