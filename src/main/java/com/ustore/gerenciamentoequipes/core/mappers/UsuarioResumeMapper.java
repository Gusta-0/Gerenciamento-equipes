package com.ustore.gerenciamentoequipes.core.mappers;

import com.ustore.gerenciamentoequipes.core.entity.Usuario;
import com.ustore.gerenciamentoequipes.payload.dto.response.UsuarioResumoResponse;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UsuarioResumeMapper {
    UsuarioResumoResponse toResumoResponse(Usuario usuario);
    List<UsuarioResumoResponse> toResumoResponseList(Set<Usuario> usuarios);
}
