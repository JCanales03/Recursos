package com.conaf.microservicio.recursos.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.conaf.microservicio.recursos.model.Recurso;
import com.conaf.microservicio.recursos.repository.RecursoRepository;

@Service
public class RecursoService {

    @Autowired
    private RecursoRepository recursoRepository;

    @Autowired
    private WebClient webClient;

    public List<Recurso> getRecursos() {
        return recursoRepository.findAll();
    }

    public Recurso saveRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public Recurso getRecursoId(Long id) {
        return recursoRepository.findById(id).orElse(null);
    }

    public Recurso updateRecurso(Recurso recurso) {
        return recursoRepository.save(recurso);
    }

    public String deleteRecurso(Long id) {
        recursoRepository.deleteById(id);
        return "Recurso eliminado";
    }

    public String asignarRecursoAIncendio(Long recursoId, Long incendioId) {
    
        webClient.get()
            .uri("https://incendios.onrender.com/api/incendios" + incendioId)
            .retrieve()
            .toBodilessEntity()
            .block();

        Recurso recurso = recursoRepository.findById(recursoId)
                            .orElseThrow(() -> new RuntimeException("Recurso no encontrado"));
        
        if (recurso.getIncendioId() != null) {
        return "El recurso ya está asignado al incendio ID: " + recurso.getIncendioId();
        }                   

        recurso.setIncendioId(incendioId);
        recurso.setEstadoRecurso("En Combate");
        recursoRepository.save(recurso);

        return "El recurso " + recurso.getNombre() + " ha sido asignado exitosamente al incendio ID: " + incendioId;
    }
}

