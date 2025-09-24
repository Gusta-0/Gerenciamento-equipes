package com.ustore.gerenciamentoequipes.service.dto.request;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioUpdateRequest(
        String nomeCompleto,
        @Email(message = "Email inválido")
        String email,
        @Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
        String novaSenha,
        NivelAcesso nivelAcesso,
        Cargo cargo,
        String telefone,
        StatusUser statusUser
){
}
