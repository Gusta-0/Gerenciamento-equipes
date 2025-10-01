package com.ustore.gerenciamentoequipes.controller;

import com.ustore.gerenciamentoequipes.infrastructure.config.TarefaAPI;
import com.ustore.gerenciamentoequipes.service.TarefaService;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaRequest;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaUpdateRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.TarefaResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
