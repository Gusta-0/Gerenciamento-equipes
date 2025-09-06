package com.ustore.gerenciamentoequipes.service.dto.request;

import com.ustore.gerenciamentoequipes.infrastructure.enums.RoleType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UsuarioRequest(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
        String nome,
        @NotBlank(message = "O sobrenome é obrigatório")
        @Size(min = 3, max = 100, message = "O sobrenome deve ter entre 3 e 100 caracteres")
        String sobrenome,
        String cpf,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,
        RoleType role,
        String telefone,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial"
        )
        String senha
) {
}
