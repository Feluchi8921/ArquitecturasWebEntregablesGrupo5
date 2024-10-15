package org.example.integrador_3.repository;

import org.example.integrador_3.model.Estudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstudianteRepository extends JpaRepository<Estudiante, Long> {
    // d) Obtiene un estudiante por su número de libreta
    @Query("SELECT e FROM Estudiante e WHERE e.nroLibreta = :nroLibreta")
    Optional<Estudiante> findByNroLibreta(@Param("nroLibreta") int nroLibreta);

    // c) Recupera todos los estudiantes ordenados por apellido
    @Query("SELECT e FROM Estudiante e WHERE e.apellido = :apellido ORDER BY e.apellido ASC")
    List<Estudiante> findAllByApellido(@Param("apellido") String apellido);

    // e) Obtiene una lista de estudiantes por género
    @Query("SELECT e FROM Estudiante e WHERE e.genero = :genero ORDER BY e.genero ASC")
    List<Estudiante> findAllByGenero(@Param("genero") String genero);

    // g) Obtiene los estudiantes de una carrera determinada filtrado por ciudad
    @Query("SELECT a " +
            "FROM Estudiante a, IN (a.inscripciones) i " +
            "WHERE i.carrera.nombreCarrera = :carrera AND a.ciudad = :ciudadOrigen")
    List<Estudiante> findAllByCarreraCiudad(@Param("carrera") String carrera,
                                            @Param("ciudadOrigen") String ciudad);

}
