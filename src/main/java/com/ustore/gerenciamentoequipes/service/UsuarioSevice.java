package com.ustore.gerenciamentoequipes.service;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.infrastructure.exception.ConflictException;
import com.ustore.gerenciamentoequipes.infrastructure.exception.UnauthorizedException;
import com.ustore.gerenciamentoequipes.infrastructure.repository.UsuarioRepository;
import com.ustore.gerenciamentoequipes.infrastructure.security.JwtUtil;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioLogin;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResponse;
import com.ustore.gerenciamentoequipes.service.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public void emailExiste(String email) {
        if (usuarioRepository.findByEmail(email).isPresent()) {
            throw new ConflictException("Email já cadastrado " + email);
        }
    }

    public UsuarioResponse salvaUsuario(UsuarioRequest usuarioRequest) {
        emailExiste(usuarioRequest.email());
        Usuario usuario = usuarioMapper.toEntity(usuarioRequest);
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


}
