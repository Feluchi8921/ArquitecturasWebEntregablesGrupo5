package org.example.integrador_3.service;

import org.example.integrador_3.dto.CantidadInscriptosDTO;
import org.example.integrador_3.dto.InformeDTO;
import org.example.integrador_3.model.Carrera;
import org.example.integrador_3.model.Estudiante;
import org.example.integrador_3.model.Inscripcion;
import org.example.integrador_3.repository.CarreraRepository;
import org.example.integrador_3.repository.EstudianteRepository;
import org.example.integrador_3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class InscripcionService {
    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CarreraRepository carreraRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    // f) Obtiene una lista de carreras ordenadas por cantidad de inscriptos
    public List<CantidadInscriptosDTO> getAllByCantInscriptos() {
        return inscripcionRepository.findAllByCantInscriptos();
    }

    // b) Matricula un estudiante en una carrera
    public void save(long idCarrera, Estudiante e) throws Exception {
        Carrera c = carreraRepository.findById(idCarrera).orElse(null);
        if (c == null) {
            throw new Exception("Carrera no encontrada");
        }
        estudianteRepository.save(e);
        Inscripcion i = new Inscripcion(c, e, LocalDate.now(), null, 0, false);
        inscripcionRepository.save(i);
    }

    // h) Genera un reporte de las carreras, que para cada carrera incluye información de los
    // inscriptos y egresados por año. Las carreras se presentan ordenadas alfabéticamente y
    // los años de manera cronológica.
    public List<InformeDTO> generarInforme() {

        List<Object[]> graduados = inscripcionRepository.findGraduados();
        List<Object[]> inscriptos = inscripcionRepository.findInscriptos();

        Map<String, Map<Integer, InformeDTO>> informeMap = new HashMap<>();

        // Procesa inscriptos
        for (Object[] row : inscriptos) {
            String nombreCarrera = (String) row[0]; // Nombre carrera
            int anio = (int)row[1]; // Año de inscripción
            long cantInscriptos = (row[2] != null) ? ((Number) row[2]).longValue() : 0; // Cantidad de inscriptos

            // Agrega o actualiza la información de inscriptos en el mapa
            informeMap
                    .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                    .computeIfAbsent(anio, k -> new InformeDTO(nombreCarrera, anio, cantInscriptos, 0))
                    .setCantInscriptos(cantInscriptos);
        }

        // Procesa graduados
        for (Object[] row : graduados) {
            String nombreCarrera = (String) row[0]; // Nombre carrera
            int anio = (row[1] != null) ? ((Number) row[1]).intValue() : 0; // Año de egreso
            long cantGraduados = (row[3] != null) ? ((Number) row[3]).longValue() : 0; // Cantidad de graduados

            informeMap
                    .computeIfAbsent(nombreCarrera, k -> new HashMap<>())
                    .computeIfAbsent(anio, k -> new InformeDTO(nombreCarrera, anio, 0, cantGraduados)) // Si no existe, se crea con cantGraduados
                    .setCantGraduados(cantGraduados); // Si ya existe, se actualiza la cantidad de graduados
        }

        // Convierte el mapa a una lista
        List<InformeDTO> informes = new ArrayList<>();
        for (Map<Integer, InformeDTO> anios : informeMap.values()) {
            informes.addAll(anios.values());
        }

        return informes;
    }

}