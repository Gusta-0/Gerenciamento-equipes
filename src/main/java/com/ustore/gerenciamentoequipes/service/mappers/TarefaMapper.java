package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Tarefa;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.TarefaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = UsuarioResumeMapper.class)
public interface TarefaMapper {

    @Mapping(target = "responsavel", ignore = true) // resolvido no Service com usuarioRepository
    Tarefa toEntity(TarefaRequest request);

    @Mapping(target = "criador", source = "criador.nomeCompleto")
    @Mapping(target = "responsavel", source = "responsavel")
    TarefaResponse toResponse(Tarefa tarefa);

    List<Tarefa> toEntityList(List<TarefaRequest> requests);
    List<TarefaResponse> toResponseList(List<Tarefa> tarefas);
}

