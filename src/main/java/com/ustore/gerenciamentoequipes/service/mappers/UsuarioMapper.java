package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResponse;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResumoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequest request);
    UsuarioResponse toResponse(Usuario usuario);
    List<Usuario> toEntityList(List<UsuarioRequest> requests);
    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);

}

