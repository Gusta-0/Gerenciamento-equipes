package com.ustore.gerenciamentoequipes.core.repository;

import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {

    Optional<Usuario> findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

//    Optional<Usuario> findByUsername(String username);

}
