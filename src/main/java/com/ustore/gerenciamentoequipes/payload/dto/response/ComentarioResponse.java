package com.ustore.gerenciamentoequipes.payload.dto.response;

import com.ustore.gerenciamentoequipes.core.entity.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComentarioResponse(UUID id,
                                 String texto,
                                 LocalDateTime dataCriacao,
                                 Usuario autor) {
}
