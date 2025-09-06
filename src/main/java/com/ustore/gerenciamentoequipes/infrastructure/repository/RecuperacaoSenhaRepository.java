package com.ustore.gerenciamentoequipes.infrastructure.repository;

import com.ustore.gerenciamentoequipes.infrastructure.entity.RecuperacaoSenha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RecuperacaoSenhaRepository extends JpaRepository<RecuperacaoSenha, UUID> {
    Optional<RecuperacaoSenha> findByToken(String token);
}
