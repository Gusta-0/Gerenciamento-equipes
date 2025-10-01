package com.ustore.gerenciamentoequipes.payload.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import jakarta.validation.constraints.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public record UsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 10, max = 100, message = "O nome deve ter entre 10 e 100 caracteres")
        String nomeCompleto,

        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial"
        )
        String senha,

        @NotNull(message = "O nível de acesso é obrigatório")
        NivelAcesso nivelAcesso,

        @NotNull(message = "O cargo é obrigatório")
        Cargo cargo,

        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) XXXXX-XXXX"
        )
        String telefone
) {
}
