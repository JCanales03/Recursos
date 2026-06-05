package com.conaf.microservicio.recursos.config;

import com.conaf.microservicio.recursos.model.Recurso;
import com.conaf.microservicio.recursos.repository.RecursoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class RecursosIniciales {

    @Bean
    CommandLineRunner DatosIniciales(RecursoRepository repository) {
        return args -> {

            if (repository.count() == 0) {

                repository.saveAll(List.of(
                    // 6 Brigadas disponibles
                    new Recurso(null, "Brigada 01", "Terrestre", "Disponible",null),
                    new Recurso(null, "Brigada 02", "Terrestre", "Disponible",null),
                    new Recurso(null, "Brigada 03", "Terrestre", "Disponible",null),
                    new Recurso(null, "Brigada 04", "Terrestre", "Disponible",null),
                    new Recurso(null, "Brigada 05", "Terrestre", "Disponible",null),
                    new Recurso(null, "Brigada 06", "Terrestre", "Disponible",null),
                    // 2 Brigadas en descanso
                    new Recurso(null, "Brigada 07", "Terrestre", "En descanso",null),
                    new Recurso(null, "Brigada 08", "Terrestre", "En descanso",null),
                    // Aviones
                    new Recurso(null, "Avión Cisterna 01", "Aereo", "Disponible",null),
                    new Recurso(null, "Avión Cisterna 02", "Aereo", "En mantencion",null),
                    // Camiones
                    new Recurso(null, "Camión Aljibe 01", "Terrestre", "Disponible",null),
                    new Recurso(null, "Camión Aljibe 02", "Terrestre", "Disponible",null),
                    new Recurso(null, "Camión Aljibe 03", "Terrestre", "Disponible",null),
                    new Recurso(null, "Camión Aljibe 04", "Terrestre", "Disponible",null)
                ));
            }
        };
    }
}
