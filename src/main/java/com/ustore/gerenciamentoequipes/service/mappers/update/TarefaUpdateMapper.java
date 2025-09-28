package com.ustore.gerenciamentoequipes.service.mappers.update;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Tarefa;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "equipe", ignore = true)
    @Mapping(target = "responsaveis", ignore = true) // também tratado no Service
    void updateTarefa(TarefaRequest request, @MappingTarget Tarefa entity);
}

