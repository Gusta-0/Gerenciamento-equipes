package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Tarefa;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.TarefaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefaMapper {
    @Mapping(target = "equipe", ignore = true) // será setado no Service via repository
    Tarefa toEntity(TarefaRequest request);
    @Mapping(target = "equipeId", source = "equipe.id")
    TarefaResponse toResponse(Tarefa tarefa);
    List<Tarefa> toEntityList(List<TarefaRequest> requests);
    List<TarefaResponse> toResponseList(List<Tarefa> tarefas);
}
