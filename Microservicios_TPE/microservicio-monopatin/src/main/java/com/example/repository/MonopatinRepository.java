package com.example.repository;

import com.example.entities.Monopatin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MonopatinRepository extends JpaRepository<Monopatin, Long> {

    // 3. e) Devolver cantidad de monopatines según su estado.
    int countByEstado(String estado);

    // 3. g) Devolver cantidad de monopatines disponibles en una zona especificada.
    @Query("SELECT m " +
            "FROM Monopatin m " +
            "WHERE :latitud BETWEEN (m.latitud - 1000) AND (m.latitud + 1000) " +
            "AND :longitud BETWEEN (m.longitud - 1000) AND (m.longitud + 1000) " +
            "AND m.estado = 'En parada'")
    List<Monopatin> findMonopatinesCercanos(@Param("latitud") Double latitud, @Param("longitud") Double longitud);

}
