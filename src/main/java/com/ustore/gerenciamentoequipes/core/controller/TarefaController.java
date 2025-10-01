package com.ustore.gerenciamentoequipes.core.controller;

import com.ustore.gerenciamentoequipes.config.TarefaAPI;
import com.ustore.gerenciamentoequipes.core.service.TarefaService;
import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaFiltroRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaRequest;
import com.ustore.gerenciamentoequipes.payload.dto.request.TarefaUpdateRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.TarefaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefaController implements TarefaAPI {

    private final TarefaService tarefaService;

    @Override
    @PostMapping
    public ResponseEntity<TarefaResponse> criarTarefa(@RequestBody @Valid TarefaRequest request) {
        return ResponseEntity.ok(tarefaService.criarTarefa(request));
    }

    @Override
    @GetMapping("/filtrar")
    public ResponseEntity<Page<TarefaResponse>> filtrar(
            @ModelAttribute TarefaFiltroRequest filtro,
            Pageable pageable
    ) {
        return ResponseEntity.ok(tarefaService.filtrar(filtro, pageable));
    }

    @Override
    @PatchMapping ("/{id}")
    public ResponseEntity<TarefaResponse> atualizarTarefa(@PathVariable("id") UUID tarefaId,
                                                          @Valid @RequestBody TarefaUpdateRequest updateRequest) {
        return ResponseEntity.ok(tarefaService.atualizarTarefa(tarefaId, updateRequest));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTarefa(@PathVariable UUID id) {
        tarefaService.deletarTarefa(id);
        return ResponseEntity.noContent().build();
    }
}
