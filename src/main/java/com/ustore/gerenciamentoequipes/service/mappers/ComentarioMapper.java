package com.ustore.gerenciamentoequipes.service.mappers;

import com.ustore.gerenciamentoequipes.infrastructure.entity.Comentario;
import com.ustore.gerenciamentoequipes.service.dto.request.ComentarioRequest;
import com.ustore.gerenciamentoequipes.service.dto.response.ComentarioResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    Comentario toEntity(ComentarioRequest request);
    ComentarioResponse toResponse(Comentario comentario);
    List<Comentario> toEntityList(List<ComentarioRequest> requests);
    List<ComentarioResponse> toResponseList(List<Comentario> comentarios);
}
