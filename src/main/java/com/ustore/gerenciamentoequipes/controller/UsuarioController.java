package com.ustore.gerenciamentoequipes.controller;

import com.ustore.gerenciamentoequipes.infrastructure.config.ClienteAPI;
import com.ustore.gerenciamentoequipes.infrastructure.enums.Cargo;
import com.ustore.gerenciamentoequipes.infrastructure.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusUser;
import com.ustore.gerenciamentoequipes.infrastructure.security.JwtUtil;
import com.ustore.gerenciamentoequipes.service.UsuarioSevice;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioLogin;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioUpdateRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Validated
public class UsuarioController implements ClienteAPI{

    private final UsuarioSevice usuarioService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Override
    @PostMapping
    public ResponseEntity<UsuarioResponse> salvaUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioRequest));
    }

    @Override
    @PostMapping("/login")
    public String login(@Valid @RequestBody UsuarioLogin dto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha())
        );
        return jwtUtil.generateToken(authentication.getName());
    }

    @Override
    @GetMapping("/usuarios/pesquisa")
    public Page<UsuarioResponse> pesquisar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @ParameterObject Pageable pageable
    ) {
        return usuarioService.pesquisar(nome, email, pageable);
    }

    @Override
    @GetMapping("/usuarios/filtros")
    public Page<UsuarioResponse> filtrar(
            @RequestParam(required = false) Cargo cargo,
            @RequestParam(required = false) StatusUser status,
            @RequestParam(required = false) NivelAcesso nivel,
            @ParameterObject Pageable pageable
    ) {
        return usuarioService.filtrar(cargo, status, nivel, pageable);
    }

    @Override
    @PatchMapping
    public ResponseEntity<UsuarioResponse> atualizaDadoUsuario(UsuarioUpdateRequest dto) {
        return ResponseEntity.ok(usuarioService.atualizar(dto));
    }

    @Override
    @DeleteMapping("/{email}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN', 'MANAGER')")
    public void inativar(@PathVariable String email) {
        usuarioService.inativarUsuario(email);
    }
}
