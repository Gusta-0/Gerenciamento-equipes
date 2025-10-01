package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Usuario;
import com.ustore.gerenciamentoequipes.service.dto.response.UsuarioResumoResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UsuarioResumeMapper {
    UsuarioResumoResponse toResumoResponse(Usuario usuario);
    List<UsuarioResumoResponse> toResumoResponseList(Set<Usuario> usuarios);
}
