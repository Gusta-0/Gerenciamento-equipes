package com.ustore.gerenciamentoequipes.core.controller;

import com.ustore.gerenciamentoequipes.config.ClienteAPI;
import com.ustore.gerenciamentoequipes.core.service.UsuarioSevice;
import com.ustore.gerenciamentoequipes.enums.Cargo;
import com.ustore.gerenciamentoequipes.enums.NivelAcesso;
import com.ustore.gerenciamentoequipes.enums.StatusUser;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioUpdateRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.UsuarioResponse;
import com.ustore.gerenciamentoequipes.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

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
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UsuarioResponse> salvaUsuario(@Valid @RequestBody UsuarioRequest usuarioRequest) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioRequest));
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
    @PatchMapping("/{id}")
    public ResponseEntity<UsuarioResponse> atualizaDadoUsuario(
            @PathVariable UUID id,
            @RequestBody @Valid UsuarioUpdateRequest dto
    ) throws AccessDeniedException {
        UsuarioResponse atualizado = usuarioService.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void inativar(@PathVariable UUID id) throws AccessDeniedException {
        usuarioService.inativarUsuario(id);
    }

}
