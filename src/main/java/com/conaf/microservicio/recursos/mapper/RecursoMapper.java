package com.conaf.microservicio.recursos.mapper;

import com.conaf.microservicio.recursos.dto.CreateRecursoRequest;
import com.conaf.microservicio.recursos.dto.UpdateRecursoRequest;
import com.conaf.microservicio.recursos.model.Recurso;

public class RecursoMapper {

    public static Recurso toModel(CreateRecursoRequest request) {
        return new Recurso(
            0L, 
            request.nombre(), 
            request.tipoRecurso(), 
            request.estadoRecurso(),
            request.IncendioId()
        );
    }

    public static Recurso toModel(Long id, UpdateRecursoRequest request) {
        return new Recurso(
            id, 
            request.nombre(), 
            request.tipoRecurso(), 
            request.estadoRecurso(),
            request.IncendioId()
        );
    }
}
