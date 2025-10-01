package com.ustore.gerenciamentoequipes.core.repository;

import com.ustore.gerenciamentoequipes.core.entity.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface TarefaRepository extends JpaRepository<Tarefa, UUID>, JpaSpecificationExecutor<Tarefa> {
//    List<Tarefa> findByUsuarioId(UUID usuarioId);
//    List<Tarefa> findByStatus(StatusTarefa status);
//    List<Tarefa> findByPrazoBefore(LocalDate data);
}
