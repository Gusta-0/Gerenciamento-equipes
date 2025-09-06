package com.ustore.gerenciamentoequipes.service.mappers.update;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Equipe;
import com.ustore.gerenciamentoequipes.service.dto.request.EquipeRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EquipeUpdateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "membros", ignore = true) // vai ser tratado no service
    void updateEquipe(EquipeRequest request, @MappingTarget Equipe entity);
}

