package com.ustore.gerenciamentoequipes.payload.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;

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
        public UsuarioResponse (Usuario usuario){
            this(
                    usuario.getId(),
                    usuario.getNomeCompleto(),
                    usuario.getEmail(),
                    usuario.getNivelAcesso(),
                    usuario.getCargo(),
                    usuario.getTelefone(),
                    usuario.getStatusUser(),
                    usuario.getImagem(),
                    usuario.getDataCadastro()
            );
        }
}
