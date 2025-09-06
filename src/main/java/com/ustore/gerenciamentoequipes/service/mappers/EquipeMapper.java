package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Equipe;
import com.ustore.gerenciamentoequipes.service.dto.request.EquipeRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.EquipeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipeMapper {
    @Mapping(target = "membros", ignore = true) // vai precisar buscar os usuários pelo service
    Equipe toEntity(EquipeRequest request);
    EquipeResponse toResponse(Equipe equipe);
    List<Equipe> toEntityList(List<EquipeRequest> requests);
    List<EquipeResponse> toResponseList(List<Equipe> equipes);
}



