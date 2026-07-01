package com.conaf.microservicio.recursos.controller;

import com.conaf.microservicio.recursos.dto.CreateRecursoRequest;
import com.conaf.microservicio.recursos.dto.UpdateRecursoRequest;
import com.conaf.microservicio.recursos.mapper.RecursoMapper;
import com.conaf.microservicio.recursos.model.Recurso;
import com.conaf.microservicio.recursos.repository.RecursoRepository;
import com.conaf.microservicio.recursos.service.RecursoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
@Tag(name = "Gestión de Recursos", description = "Endpoints para la administración de recursos forestales")
public class RecursoController {

    @Autowired
    private RecursoService service;

    @Autowired
    private RecursoRepository repository;

    @GetMapping("/filtro/estado/{estado}")
    @Operation(summary = "Listar recursos por estado")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Recurso>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(repository.findByEstadoRecurso(estado));
    }

    @GetMapping("/filtro/tipo/{tipo}")
    @Operation(summary = "Listar recursos por tipo")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    public ResponseEntity<List<Recurso>> buscarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(repository.findByTipoRecurso(tipo));
    }

    @GetMapping
    @Operation(summary = "Obtener todos los recursos")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    public ResponseEntity<List<Recurso>> getAll() {
        return ResponseEntity.ok(service.getRecursos());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar recurso por ID")
    @ApiResponse(responseCode = "200", description = "Recurso encontrado")
    @ApiResponse(responseCode = "404", description = "No encontrado")
    public ResponseEntity<Recurso> RecursoPorId(@PathVariable Long id) {
        Recurso recurso = service.getRecursoId(id);
        return (recurso == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(recurso);
    }

    @PostMapping
    @Operation(summary = "Crear un nuevo recurso")
    @ApiResponse(responseCode = "200", description = "Recurso creado")
    public ResponseEntity<Recurso> CrearRecurso(
            @RequestBody(description = "Datos del recurso", required = true,
            content = @Content(examples = @ExampleObject(value = "{\"nombre\": \"Camión\", \"tipo\": \"Vehículo\"}")))
            @Valid @org.springframework.web.bind.annotation.RequestBody CreateRecursoRequest request) {
        Recurso recurso = RecursoMapper.toModel(request);
        return ResponseEntity.ok(service.saveRecurso(recurso));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un recurso existente")
    @ApiResponse(responseCode = "200", description = "Recurso actualizado")
    public ResponseEntity<Recurso> Actualizar(
            @PathVariable Long id, 
            @Valid @org.springframework.web.bind.annotation.RequestBody UpdateRecursoRequest request) {
        Recurso recurso = RecursoMapper.toModel(id, request);
        return ResponseEntity.ok(service.updateRecurso(recurso));
    }

    @PutMapping("/{id}/estado")
    @Operation(summary = "Actualizar estado de un recurso")
    @ApiResponse(responseCode = "200", description = "Estado actualizado")
    public ResponseEntity<Recurso> ActualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        Recurso recurso = service.getRecursoId(id);
        if (recurso == null) return ResponseEntity.notFound().build();
        recurso.setEstadoRecurso(estado);
        return ResponseEntity.ok(service.updateRecurso(recurso));
    }

    @PutMapping("/{recursoId}/asignar/{incendioId}")
    @Operation(summary = "Asignar recurso a un incendio")
    @ApiResponse(responseCode = "200", description = "Asignación exitosa")
    public ResponseEntity<String> asignarRecurso(@PathVariable Long recursoId, @PathVariable Long incendioId) {
        String respuesta = service.asignarRecursoAIncendio(recursoId, incendioId);
        return ResponseEntity.ok(respuesta);
    }
}