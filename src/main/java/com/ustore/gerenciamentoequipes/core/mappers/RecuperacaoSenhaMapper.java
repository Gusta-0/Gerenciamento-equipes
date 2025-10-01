package com.ustore.gerenciamentoequipes.core.mappers;

import com.ustore.gerenciamentoequipes.core.entity.RecuperacaoSenha;
import com.ustore.gerenciamentoequipes.payload.dto.request.RecuperacaoSenhaRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.RecuperacaoSenhaResponse;
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

