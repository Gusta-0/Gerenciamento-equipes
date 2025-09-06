package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.RecuperacaoSenha;
import com.ustore.gerenciamentoequipes.service.dto.request.RecuperacaoSenhaRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.RecuperacaoSenhaResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecuperacaoSenhaMapper {
    @Mapping(target = "usuario", ignore = true) // resolvido no Service
    RecuperacaoSenha toEntity(RecuperacaoSenhaRequest request);
    @Mapping(target = "usuarioId", source = "usuario.id")
    RecuperacaoSenhaResponse toResponse(RecuperacaoSenha entity);
    List<RecuperacaoSenha> toEntityList(List<RecuperacaoSenhaRequest> requests);
    List<RecuperacaoSenhaResponse> toResponseList(List<RecuperacaoSenha> entities);
}

