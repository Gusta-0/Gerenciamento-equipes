package com.ustore.gerenciamentoequipes.service.dto.response;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;

import java.time.LocalDateTime;
import java.util.UUID;

public record ComentarioResponse(UUID id,
                                 String texto,
                                 LocalDateTime dataCriacao,
                                 Usuario autor) {
}
