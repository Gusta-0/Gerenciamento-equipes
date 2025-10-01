package com.ustore.gerenciamentoequipes.service.mappers.update;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Tarefa;
import com.ustore.gerenciamentoequipes.service.dto.request.TarefaUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateMapper {

    @Mapping(target = "criador", ignore = true) // opcional, se nunca quiser atualizar criador
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "responsavel", ignore = true) // também tratado no Service
    void updateTarefa(TarefaUpdateRequest updateRequest, @MappingTarget Tarefa entity);
}

