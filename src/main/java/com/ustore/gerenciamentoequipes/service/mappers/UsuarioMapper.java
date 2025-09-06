package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.service.dto.request.UsuarioRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    Usuario toEntity(UsuarioRequest request);
    UsuarioResponse toResponse(Usuario usuario);
    List<Usuario> toEntityList(List<UsuarioRequest> requests);
    List<UsuarioResponse> toResponseList(List<Usuario> usuarios);
}

