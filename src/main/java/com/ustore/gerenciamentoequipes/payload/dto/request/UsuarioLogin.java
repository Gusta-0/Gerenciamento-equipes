package com.ustore.gerenciamentoequipes.payload.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLogin {
    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Formato de email inválido")
    String email;

    @NotBlank(message = "Senha é obrigatória")
    String senha;
}
