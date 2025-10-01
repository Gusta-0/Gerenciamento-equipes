package com.ustore.gerenciamentoequipes.core.mappers;

import com.ustore.gerenciamentoequipes.core.entity.Comentario;
import com.ustore.gerenciamentoequipes.payload.dto.request.ComentarioRequest;
import com.ustore.gerenciamentoequipes.payload.dto.response.ComentarioResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ComentarioMapper {
    Comentario toEntity(ComentarioRequest request);
    ComentarioResponse toResponse(Comentario comentario);
    List<Comentario> toEntityList(List<ComentarioRequest> requests);
    List<ComentarioResponse> toResponseList(List<Comentario> comentarios);
}
