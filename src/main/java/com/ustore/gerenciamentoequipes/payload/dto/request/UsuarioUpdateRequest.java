package com.ustore.gerenciamentoequipes.payload.dto.request;

import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;
import jakarta.validation.constraints.*;
import org.springframework.security.crypto.password.PasswordEncoder;

public record UsuarioUpdateRequest(
        @Size(min = 10, max = 100, message = "O nome deve ter entre 10 e 100 caracteres")
        String nomeCompleto,

        @Email(message = "Email inválido")
        String email,

        @Size(min = 8, max = 64, message = "A senha deve ter entre 8 e 64 caracteres")
        @Pattern(
                regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
                message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial"
        )
        String novaSenha,

        NivelAcesso nivelAcesso,

        Cargo cargo,

        @Pattern(
                regexp = "^\\(?\\d{2}\\)?\\s?\\d{4,5}-?\\d{4}$",
                message = "Telefone inválido. Use o formato (XX) XXXXX-XXXX"
        )
        String telefone,

        StatusUser statusUser
){
        public void atualizarEntidade(Usuario usuario, PasswordEncoder passwordEncoder) {
                if (this.nomeCompleto != null && !this.nomeCompleto.isBlank()) {
                        usuario.setNomeCompleto(this.nomeCompleto);
                }
                if (this.email != null && !this.email.isBlank()) {
                        usuario.setEmail(this.email);
                }
                if (this.novaSenha != null && !this.novaSenha.isBlank()) {
                        usuario.setSenha(passwordEncoder.encode(this.novaSenha));
                }
                if (this.nivelAcesso != null) {
                        usuario.setNivelAcesso(this.nivelAcesso);
                }
                if (this.cargo != null) {
                        usuario.setCargo(this.cargo);
                }
                if (this.telefone != null && !this.telefone.isBlank()) {
                        usuario.setTelefone(this.telefone);
                }
                if (this.statusUser != null) {
                        usuario.setStatusUser(this.statusUser);
                }
        }
}
