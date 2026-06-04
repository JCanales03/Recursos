package com.conaf.microservicio.recursos.repository;

import com.conaf.microservicio.recursos.model.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {
    List<Recurso> findByEstadoRecurso(String estadoRecurso);
    List<Recurso> findByTipoRecurso(String tipoRecurso);
}
