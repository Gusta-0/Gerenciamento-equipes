package com.ustore.gerenciamentoequipes.core.entity;

import com.ustore.gerenciamentoequipes.enums.Prioridade;
import com.ustore.gerenciamentoequipes.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "tarefas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tarefa {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String titulo;
    private String descricao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "criador_id", nullable = false)
    private Usuario criador;
    private StatusTarefa status = StatusTarefa.A_FAZER;
    private Prioridade prioridade;
    private LocalDateTime dataEntrega;
    private String projeto;
    private String tags;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario responsavel;
}
