package com.ustore.gerenciamentoequipes.infrastructure.repository;


import com.ustore.gerenciamentoequipes.infrastructure.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
}
