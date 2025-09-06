package com.ustore.gerenciamentoequipes.service.mappers.update;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioUpdateMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "senha", ignore = true) // senha pode ser alterada só em fluxo separado
    void updateUsuario(UsuarioRequest request, @MappingTarget Usuario entity);
}

