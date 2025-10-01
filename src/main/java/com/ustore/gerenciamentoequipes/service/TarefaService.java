package com.ustore.gerenciamentoequipes.service;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Tarefa;
import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.infrastructure.enums.StatusTarefa;
import com.ustore.gerenciamentoequipes.infrastructure.especifications.TarefaSpecification;
import com.ustore.gerenciamentoequipes.infrastructure.exception.ResourceNotFoundException;
import com.ustore.gerenciamentoequipes.infrastructure.repository.TarefaRepository;
import com.ustore.gerenciamentoequipes.infrastructure.repository.UsuarioRepository;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaFiltroRequest;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaRequest;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaUpdateRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.TarefaResponse;
import com.ustore.gerenciamentoequipes.service.mappers.TarefaMapper;
import com.ustore.gerenciamentoequipes.service.mappers.update.TarefaUpdateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;
    private final TarefaMapper tarefaMapper;
    private final TarefaUpdateMapper tarefaUpdateMapper;


    public TarefaResponse criarTarefa(TarefaRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();

        Usuario usuarioExistente = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));

        Tarefa tarefa = tarefaMapper.toEntity(request);

        tarefa.setCriador(usuarioExistente);
        tarefa.setStatus(StatusTarefa.A_FAZER);

        Usuario responsavel = usuarioRepository.findById(request.responsavelId())
                .orElseThrow(() -> new ResourceNotFoundException("Responsável não encontrado"));
        tarefa.setResponsavel(responsavel);

        tarefaRepository.save(tarefa);

        return tarefaMapper.toResponse(tarefa);
    }

    public Page<TarefaResponse> filtrar(TarefaFiltroRequest filtro, Pageable pageable) {
        return tarefaRepository.findAll(
                TarefaSpecification.comFiltros(filtro),
                pageable
        ).map(tarefaMapper::toResponse);
    }

    public TarefaResponse atualizarTarefa(UUID tarefaId, TarefaUpdateRequest updateRequest) {
        Tarefa tarefaExistente = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        tarefaUpdateMapper.updateTarefa(updateRequest, tarefaExistente);

        tarefaRepository.save(tarefaExistente);

        return tarefaMapper.toResponse(tarefaExistente);
    }

    public void deletarTarefa(UUID tarefaId) {
        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new ResourceNotFoundException("Tarefa não encontrada"));

        tarefaRepository.delete(tarefa);
    }

}
