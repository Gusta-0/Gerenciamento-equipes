package com.ustore.gerenciamentoequipes.service;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.infrastructure.especifications.UsuarioSpecification;
import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;
import com.ustore.gerenciamentoequipes.infrastructure.exception.ConflictException;
import com.ustore.gerenciamentoequipes.infrastructure.exception.ResourceNotFoundException;
import com.ustore.gerenciamentoequipes.infrastructure.exception.UnauthorizedException;
import com.ustore.gerenciamentoequipes.infrastructure.repository.UsuarioRepository;
import com.ustore.gerenciamentoequipes.infrastructure.security.JwtUtil;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioFilter;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioLogin;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioUpdateRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResponse;
import com.ustore.gerenciamentoequipes.service.mappers.UsuarioMapper;
import com.ustore.gerenciamentoequipes.service.mappers.update.UsuarioUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@RequestMapping("/usuario")
public class UsuarioSevice {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UsuarioUpdateMapper usuarioUpdateMapper;

    public void emailExiste(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new ConflictException("Email" + email + "já cadastrado!");
        }
    }

    public UsuarioResponse salvaUsuario(UsuarioRequest usuarioRequest) {
        emailExiste(usuarioRequest.email());
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
        usuario.setStatusUser(StatusUser.ATIVO);
        usuario.setSenha(passwordEncoder.encode(usuarioRequest.senha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return usuarioMapper.toResponse(usuarioSalvo);
    }

    public String autenticarCliente(UsuarioLogin dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(dto.getEmail(),
                            dto.getSenha())
            );
            return "Bearer " + jwtUtil.generateToken(authentication.getName());

        } catch (BadCredentialsException | UsernameNotFoundException | AuthorizationDeniedException e) {
            throw new UnauthorizedException("Usuário ou senha inválidos: ", e.getCause());
        }
    }

    public Page<UsuarioResponse> pesquisar(String nome, String email, Pageable pageable) {
        return usuarioRepository.findAll(
                UsuarioSpecification.comPesquisa(nome, email),
                pageable
        ).map(usuarioMapper::toResponse);
    }

    public Page<UsuarioResponse> filtrar(Cargo cargo, StatusUser status, NivelAcesso nivel, Pageable pageable) {
        return usuarioRepository.findAll(
                UsuarioSpecification.comFiltros(cargo, status, nivel),
                pageable
        ).map(usuarioMapper::toResponse);
    }

    @Transactional
    public UsuarioResponse atualizar(UsuarioUpdateRequest dto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Usuario usuarioExistente = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuarioUpdateMapper.updateUsuario(dto, usuarioExistente);

        if (dto.novaSenha() != null && !dto.novaSenha().isBlank()) {
            usuarioExistente.setSenha(passwordEncoder.encode(dto.novaSenha()));
        }
        Usuario salvo = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.toResponse(salvo);
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
                .map(usuarioMapper::toResponse);
    }


    @Transactional
    public void inativarUsuario(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuario.setStatusUser(StatusUser.INATIVO);
        usuarioRepository.save(usuario);
    }

}
