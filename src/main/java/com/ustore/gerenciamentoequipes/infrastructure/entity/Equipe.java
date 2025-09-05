package com.ustore.gerenciamentoequipes.infrastructure.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity(name = "equipe")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Equipe {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Usuario> membros;
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Tarefa> tarefas;
}
