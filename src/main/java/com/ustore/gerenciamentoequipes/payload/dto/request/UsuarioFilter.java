package com.ustore.gerenciamentoequipes.payload.dto.request;

import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;

public record UsuarioFilter(String nome,
                            String email,
                            Cargo cargo,
                            NivelAcesso nivel,
                            StatusUser status) {
}
