package com.ustore.gerenciamentoequipes.infrastructure.especifications;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;
import org.springframework.data.jpa.domain.Specification;

public class UsuarioSpecification {

    public static Specification<Usuario> comNome(String nome) {
        return (root, query, cb) ->
                nome == null ? null : cb.like(cb.lower(root.get("nomeCompleto")), "%" + nome.toLowerCase() + "%");
    }

    public static Specification<Usuario> comEmail(String email) {
        return (root, query, cb) ->
                email == null ? null : cb.like(cb.lower(root.get("email")), "%" + email.toLowerCase() + "%");
    }

    public static Specification<Usuario> comNivelAcesso(NivelAcesso nivelAcesso) {
        return (root, query, cb) ->
                nivelAcesso == null ? null : cb.equal(root.get("nivelAcesso"), nivelAcesso);
    }

    public static Specification<Usuario> comStatus(StatusUser status) {
        return (root, query, cb) ->
                status == null ? null : cb.equal(root.get("statusUsuario"), status);
    }

    public static Specification<Usuario> comCargo(Cargo cargo) {
        return (root, query, cb) ->
                cargo == null ? null : cb.equal(root.get("cargo"), cargo);
    }

    public static Specification<Usuario> comPesquisa(String nome, String email) {
        return Specification.anyOf(
                comNome(nome),
                comEmail(email)
        );

    }

    public static Specification<Usuario> comFiltros(Cargo cargo, StatusUser status, NivelAcesso nivel) {
        return Specification.allOf(
                comCargo(cargo),
                comStatus(status),
                comNivelAcesso(nivel));
    }
}
