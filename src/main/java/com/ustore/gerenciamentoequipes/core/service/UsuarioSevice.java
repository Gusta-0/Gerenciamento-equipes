package com.ustore.gerenciamentoequipes.core.service;

import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import com.ustore.gerenciamentoequipes.core.especifications.UsuarioSpecification;
import com.ustore.gerenciamentoequipes.core.repository.UsuarioRepository;
import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;
import com.ustore.gerenciamentoequipes.exception.ConflictException;
import com.ustore.gerenciamentoequipes.exception.ResourceNotFoundException;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioFilter;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioUpdateRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.UsuarioResponse;
import com.ustore.gerenciamentoequipes.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public void emailExiste(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new ConflictException("Email" + email + "já cadastrado!");
        }
    }

    public UsuarioResponse salvaUsuario(UsuarioRequest usuarioRequest) {
        emailExiste(usuarioRequest.email());
        Usuario usuario = usuarioRequest.toUsuario();

        usuario.setStatusUser(StatusUser.ATIVO);
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.senha()));

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return new UsuarioResponse(usuarioSalvo);
    }

    public Page<UsuarioResponse> pesquisar(String nome, String email, Pageable pageable) {
        return usuarioRepository.findAll(
                UsuarioSpecification.comPesquisa(nome, email),
                pageable
        ).map(UsuarioResponse::new);
    }

    public Page<UsuarioResponse> filtrar(Cargo cargo, StatusUser status, NivelAcesso nivel, Pageable pageable) {
        return usuarioRepository.findAll(
                UsuarioSpecification.comFiltros(cargo, status, nivel),
                pageable
        ).map(UsuarioResponse::new);
    }

    @Transactional
    public UsuarioResponse atualizar(UUID id, UsuarioUpdateRequest dto) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = auth.getName();

        Usuario usuarioLogado = usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário logado não encontrado"));

        Usuario usuarioAlvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário alvo não encontrado"));

        if (!usuarioLogado.getNivelAcesso().equals(NivelAcesso.ADMIN)
                && !usuarioAlvo.getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para atualizar este usuário.");
        }

        dto.atualizarEntidade(usuarioAlvo, passwordEncoder);

        Usuario salvo = usuarioRepository.save(usuarioAlvo);
        return new UsuarioResponse(salvo);
    }

    public Page<UsuarioResponse> buscar(UsuarioFilter filter, Pageable pageable) {
        Specification<Usuario> spec = Specification.allOf(
                UsuarioSpecification.comNome(filter.nome()),
                UsuarioSpecification.comEmail(filter.email()),
                UsuarioSpecification.comNivelAcesso(filter.nivel()),
                UsuarioSpecification.comStatus(filter.status()),
                UsuarioSpecification.comCargo(filter.cargo())
        );

        return usuarioRepository.findAll(spec, pageable)
                .map(UsuarioResponse::new);
    }


    @Transactional
    public void inativarUsuario(UUID id) throws AccessDeniedException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String emailLogado = auth.getName();

        Usuario usuarioLogado = usuarioRepository.findByEmail(emailLogado)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário logado não encontrado"));

        Usuario usuarioAlvo = usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário alvo não encontrado"));

        if (!usuarioLogado.getNivelAcesso().equals(NivelAcesso.ADMIN)
                && !usuarioAlvo.getId().equals(usuarioLogado.getId())) {
            throw new AccessDeniedException("Você não tem permissão para inativar este usuário.");
        }

        usuarioAlvo.setStatusUser(StatusUser.INATIVO);
        usuarioRepository.save(usuarioAlvo);
    }

}
