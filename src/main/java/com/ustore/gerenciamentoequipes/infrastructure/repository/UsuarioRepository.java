package com.ustore.gerenciamentoequipes.infrastructure.repository;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

}
