package com.ustore.gerenciamentoequipes.service.dto.request;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;

public record UsuarioFilter(String nome,
                            String email,
                            Cargo cargo,
                            NivelAcesso nivel,
                            StatusUser status) {
}
