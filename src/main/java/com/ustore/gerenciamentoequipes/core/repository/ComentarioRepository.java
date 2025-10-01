package com.ustore.gerenciamentoequipes.core.repository;


import com.ustore.gerenciamentoequipes.core.entity.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ComentarioRepository extends JpaRepository<Comentario, UUID> {
}
