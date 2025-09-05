package com.ustore.gerenciamentoequipes.infrastructure.entity;

import com.ustore.gerenciamentoequipes.infrastructure.enums.Prioridade;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity(name = "tarefa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Tarefa {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String titulo;
    private String comentario;
    private LocalDateTime dataCriacao;
    private LocalDate prazo;
    private LocalDateTime dataTermino;
    private Prioridade prioridade;
    private StatusTarefa status;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario responsavel;
}
