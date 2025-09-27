package com.ustore.gerenciamentoequipes.infrastructure.entity;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity(name = "usuario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nomeCompleto;
    @Column(unique = true, nullable = false)
    private String email;
    private String senha;
    @Enumerated(EnumType.STRING)
    private NivelAcesso nivelAcesso;
    @Enumerated(EnumType.STRING)
    private Cargo cargo;
    private String telefone;
    private String imagem;
    private LocalDateTime dataCadastro;
    @Enumerated(EnumType.STRING)
    private StatusUser statusUser = StatusUser.ATIVO;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // retorna a lista de permissões do usuário
        return List.of(() -> "ROLE_" + this.getUsername() + "_" + this.nivelAcesso.name());
    }
    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
