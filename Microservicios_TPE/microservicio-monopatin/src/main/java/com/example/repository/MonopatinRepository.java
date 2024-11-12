package com.example.repository;

import com.example.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    // 3. a) Genera reporte de monopatines para verificar mantenimiento.
    @Query("SELECT m " +
            "FROM Monopatin m " +
            "WHERE m.kmRecorridos >= :kmMantenimiento")
    List<Monopatin> generarReporteMantenimiento(@Param("kmMantenimiento") int kmMantenimiento);

    // 3. e) Cantidad de monopatines según su estado.
    int countByEstado(String estado);

    // 3. g) Cantidad de monopatines disponibles en una zona especificada (un kilómetro a la redonda).
    @Query("SELECT m " +
            "FROM Monopatin m " +
            "WHERE :latitud BETWEEN (m.latitud - 1000) AND (m.latitud + 1000) " +
            "AND :longitud BETWEEN (m.longitud - 1000) AND (m.longitud + 1000) " +
            "AND m.estado = 'En parada'")
    List<Monopatin> findMonopatinesCercanos(@Param("latitud") Double latitud, @Param("longitud") Double longitud);

}
