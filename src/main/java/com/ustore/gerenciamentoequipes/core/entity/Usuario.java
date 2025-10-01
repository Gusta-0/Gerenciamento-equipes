package com.ustore.gerenciamentoequipes.core.entity;

import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.*;

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
    @Column(name = "username", unique = true, nullable = false)
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

    @OneToMany(mappedBy = "responsavel")
    private Set<Tarefa> tarefas = new HashSet<>();

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
