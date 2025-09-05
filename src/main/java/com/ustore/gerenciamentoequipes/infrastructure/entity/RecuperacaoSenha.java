package com.ustore.gerenciamentoequipes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "RecuperacaoSenha")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RecuperacaoSenha {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String token;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataExpiracao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
