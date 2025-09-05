package com.ustore.gerenciamentoequipes.infrastructure.entity;

import com.ustore.gerenciamentoequipes.infrastructure.enums.RoleType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;


import java.util.UUID;

@Entity(name = "usuarios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType role;
    private String telefone;
    private String senha;
}
