package com.ustore.gerenciamentoequipes.core.mappers.update;

import com.ustore.gerenciamentoequipes.core.entity.RecuperacaoSenha;
import com.ustore.gerenciamentoequipes.payload.dto.request.RecuperacaoSenhaRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface RecuperacaoSenhaUpdateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "usuario", ignore = true)
    void updateRecuperacaoSenha(RecuperacaoSenhaRequest request, @MappingTarget RecuperacaoSenha entity);
}
