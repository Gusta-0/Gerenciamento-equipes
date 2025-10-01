package com.ustore.gerenciamentoequipes.core.entity;

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
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String token;
    private LocalDateTime dataSolicitacao;
    private LocalDateTime dataExpiracao;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
