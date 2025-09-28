package com.ustore.gerenciamentoequipes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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
    private String comentario;
    private String status;
    private String prioridade;
    private LocalDateTime dataEntrega;
    private String projeto;
    private String tags;

    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tarefa_usuario",
            joinColumns = @JoinColumn(name = "tarefa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> responsaveis = new HashSet<>();

}
