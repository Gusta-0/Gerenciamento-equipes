package com.ustore.gerenciamentoequipes.infrastructure.repository;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Tarefa;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID> {
    List<Tarefa> findByEquipeId(UUID equipeId);
    List<Tarefa> findByUsuarioId(UUID usuarioId);
    List<Tarefa> findByStatus(StatusTarefa status);
    List<Tarefa> findByPrazoBefore(LocalDate data);
}
