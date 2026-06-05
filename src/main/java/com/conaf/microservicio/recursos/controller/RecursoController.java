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

import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoController {

    @Autowired
    private RecursoService service;

    @Autowired
    private RecursoRepository repository;

    @GetMapping("/filtro/estado/{estado}")
        public ResponseEntity<List<Recurso>> buscarPorEstado(@PathVariable String estado) {
        return ResponseEntity.ok(repository.findByEstadoRecurso(estado));
}

    @GetMapping("/filtro/tipo/{tipo}")
    public ResponseEntity<List<Recurso>> buscarPorTipo(@PathVariable String tipo) {
        return ResponseEntity.ok(repository.findByTipoRecurso(tipo));
    }

    @GetMapping
    public ResponseEntity<List<Recurso>> getAll() {
        return ResponseEntity.ok(service.getRecursos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recurso> RecursoPorId(@PathVariable Long id) {
        Recurso recurso = service.getRecursoId(id);
        if (recurso == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(recurso);
    }

    @PostMapping
    public ResponseEntity<Recurso> CrearRecurso(@Valid @RequestBody CreateRecursoRequest request) {
        Recurso recurso = RecursoMapper.toModel(request);
        return ResponseEntity.ok(service.saveRecurso(recurso));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Recurso> Actualizar(@PathVariable Long id, @Valid @RequestBody UpdateRecursoRequest request) {
        Recurso recurso = RecursoMapper.toModel(id, request);
        return ResponseEntity.ok(service.updateRecurso(recurso));
    }
    
    @PutMapping("/{id}/estado")
    public ResponseEntity<Recurso> ActualizarEstado(@PathVariable Long id, @RequestParam String estado) {
        Recurso recurso = service.getRecursoId(id);
        if (recurso == null) return ResponseEntity.notFound().build();
        recurso.setEstadoRecurso(estado);
        return ResponseEntity.ok(service.updateRecurso(recurso));
    }

    @PutMapping("/{recursoId}/asignar/{incendioId}")
    public ResponseEntity<String> asignarRecurso(@PathVariable Long recursoId, @PathVariable Long incendioId) {
        String respuesta = service.asignarRecursoAIncendio(recursoId, incendioId);
        return ResponseEntity.ok(respuesta);
    }

}