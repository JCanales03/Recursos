package com.conaf.microservicio.recursos.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "recursos")
public class Recurso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;          // Ej: "Avión Cisterna Arauco 01"
    private String tipoRecurso;     // "Aereo" o "Terrestre"
    private String estadoRecurso;   // "Disponible", "En mantencion", "Asignado"

    private Long IncendioId;
}
