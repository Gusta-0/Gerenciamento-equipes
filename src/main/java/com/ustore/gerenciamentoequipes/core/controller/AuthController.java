package com.ustore.gerenciamentoequipes.core.controller;

import com.ustore.gerenciamentoequipes.config.AuthAPI;
import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import com.ustore.gerenciamentoequipes.core.repository.UsuarioRepository;
import com.ustore.gerenciamentoequipes.exception.ResourceNotFoundException;
import com.ustore.gerenciamentoequipes.payload.dto.request.LoginRequest;
import com.ustore.gerenciamentoequipes.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthAPI {

    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginRequest dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.email(), dto.password())
        );
        return jwtUtil.generateToken(authentication.getName());
    }

    @Override
    @PostMapping("/esqueci-senha")
    public ResponseEntity<Void> recuperarSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        System.out.println("Link de recuperação enviado para: " + usuario.getEmail());

        return ResponseEntity.noContent().build();
    }

    @Override
    @PostMapping("/redefinir-senha")
    public ResponseEntity<Void> redefinirSenha(String email, String novaSenha) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }
}

