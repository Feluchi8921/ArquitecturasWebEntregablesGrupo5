package org.example.integrador_3.repository;

import org.example.integrador_3.dto.CantidadInscriptosDTO;
import org.example.integrador_3.dto.InformeDTO;
import org.example.integrador_3.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    // f) Obtiene una lista de carreras ordenadas por cantidad de inscriptos
    @Query("SELECT new org.example.integrador_3.dto.CantidadInscriptosDTO(c.nombreCarrera, COUNT(i.estudiante)) " +
            "FROM Inscripcion i " +
            "JOIN i.carrera c " +
            "GROUP BY c.nombreCarrera " +
            "ORDER BY COUNT(i.estudiante) DESC, c.nombreCarrera ASC")
    List<CantidadInscriptosDTO> findAllByCantInscriptos();

    // h) Generar un reporte de las carreras, que para cada carrera incluya información de los
    // inscriptos y egresados por año. Se deben ordenar las carreras alfabéticamente, y presentar
    // los años de manera cronológica.

    // Se divide en dos consultas para poder realizar las consultas con JPQL,
    // findInscriptos obtiene la cantidad de graduados
    // findGraduados obtiene la cantidad de inscriptos
    // Luego en el servicio se trabaja con los datos obtenidos de las dos consultas
    @Query(
            "SELECT c.nombreCarrera, YEAR(i.fechaInscripcion) AS anio, COUNT(i.idInscripcion) AS inscriptos, 0 AS graduado " +
                    "FROM Inscripcion i JOIN i.carrera c " +
                    "GROUP BY c.nombreCarrera, YEAR(i.fechaInscripcion)"
    )
    List<Object[]> findInscriptos();

    @Query(
            "SELECT c.nombreCarrera, YEAR(i.fechaEgreso) AS anio, 0 AS inscriptos, COUNT(i.idInscripcion) AS graduado " +
                    "FROM Inscripcion i JOIN i.carrera c " +
                    "WHERE i.graduado = true AND i.fechaEgreso IS NOT NULL " +  // Filtra solo inscripciones donde el estudiante se haya graduado
                    "GROUP BY c.nombreCarrera, YEAR(i.fechaEgreso)"
    )
    List<Object[]> findGraduados();


}