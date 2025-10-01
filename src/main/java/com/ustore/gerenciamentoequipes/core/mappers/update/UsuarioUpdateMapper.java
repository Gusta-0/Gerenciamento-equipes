package com.ustore.gerenciamentoequipes.core.mappers.update;

import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import com.ustore.gerenciamentoequipes.payload.dto.request.UsuarioUpdateRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioUpdateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", source = "novaSenha")
    void updateUsuario(UsuarioUpdateRequest request, @MappingTarget Usuario entity);
}

