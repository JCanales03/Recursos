package com.conaf.microservicio.recursos.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateRecursoRequest(
    @NotBlank(message = "El nombre es obligatorio") String nombre,
    @NotBlank(message = "El tipo de recurso es obligatorio") String tipoRecurso,
    @NotBlank(message = "El estado del recurso es obligatorio") String estadoRecurso,
    @NotBlank(message = "Es obligatorio colocar el IncendioId") Long IncendioId
) {}
