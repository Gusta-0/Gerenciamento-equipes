package com.ustore.gerenciamentoequipes.infrastructure.repository;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EquipeRepository extends JpaRepository<Equipe, UUID> {
    List<Equipe> findByNomeContainingIgnoreCase(String nome);
    List<Equipe> findByUsuariosId(UUID usuarioId);
}
