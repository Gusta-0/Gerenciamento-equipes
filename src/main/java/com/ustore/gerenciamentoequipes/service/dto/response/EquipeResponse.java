package com.ustore.gerenciamentoequipes.service.dto.response;

import java.util.List;
import java.util.UUID;

public record EquipeResponse(
        UUID id,
        String nome,
        List<UsuarioResponse> usuarios
) {
}
