package com.ustore.gerenciamentoequipes.service;

import com.ustore.gerenciamentoequipes.infrastructure.repository.UsuarioRepository;
import com.ustore.gerenciamentoequipes.infrastructure.security.JwtUtil;
import com.ustore.gerenciamentoequipes.service.mappers.UsuarioMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UsuarioSevice {
    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
}
