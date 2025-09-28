package com.ustore.gerenciamentoequipes.service.dto.response;

import java.util.UUID;

public record UsuarioResumoResponse(
        UUID id,
        String nome,
        String email
) {}
