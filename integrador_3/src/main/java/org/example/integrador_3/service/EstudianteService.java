package org.example.integrador_3.service;

import lombok.extern.slf4j.Slf4j;
import org.example.integrador_3.dto.EstudianteDTO;
import org.example.integrador_3.mappers.EstudianteMapper;
import org.example.integrador_3.model.Estudiante;
import org.example.integrador_3.repository.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service("EstudianteServicio")
public class EstudianteService {
    @Autowired
    EstudianteRepository estudianteRepository;

    @Autowired
    EstudianteMapper estudianteMapper;

    public List<EstudianteDTO> getAll() throws Exception {
        return estudianteMapper.toEstudiantesDTO(estudianteRepository.findAll());
    }

    public EstudianteDTO getById(long id) throws Exception {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isEmpty()) {
            throw new Exception("No se encontró el estudiante con ID: " + id);
        }
        return estudianteMapper.toEstudianteDTO(estudiante.get());
    }

    // d) Obtiene un estudiante por su número de libreta
    public EstudianteDTO getByNroLibreta(int nroLibreta) throws Exception {
        Optional<Estudiante> estudiante = estudianteRepository.findByNroLibreta(nroLibreta);
        if (estudiante.isEmpty()) {
            throw new Exception("No se encontró el estudiante con número de libreta: " + nroLibreta);
        }
        return estudianteMapper.toEstudianteDTO(estudiante.get());
    }

    // c) Obtiene una lista de estudiantes por apellido
    public List<EstudianteDTO> getAllByApellido(String apellido) {
        return estudianteMapper.toEstudiantesDTO(estudianteRepository.findAllByApellido(apellido));
    }

    // e) Obtiene una lista de estudiantes por género
    public List<EstudianteDTO> getAllByGenero(String genero) {
        return estudianteMapper.toEstudiantesDTO(estudianteRepository.findAllByGenero(genero));
    }

    // g) Obtiene los estudiantes de una carrera determinada filtrado por ciudad
    public List<EstudianteDTO> getAllByCarreraCiudad(String carrera, String ciudad) {
        return estudianteMapper.toEstudiantesDTO(estudianteRepository.findAllByCarreraCiudad(carrera, ciudad));
    }

    // a) Crea un nuevo estudiante
    public EstudianteDTO save(Estudiante e) throws Exception {
        Optional<Estudiante> estudiante = estudianteRepository.findById(e.getIdEstudiante());
        if (estudiante.isPresent()) {
            throw new Exception("El estudiante ya existe");
        }
        estudianteRepository.save(e);
        return estudianteMapper.toEstudianteDTO(e);
    }

    public EstudianteDTO delete(Long id) throws Exception {
        Optional<Estudiante> estudiante = estudianteRepository.findById(id);
        if (estudiante.isEmpty()) {
            throw new Exception("Estudiante " + id + " no encontrado");
        }
        estudianteRepository.delete(estudiante.get());
        return estudianteMapper.toEstudianteDTO(estudiante.get());
    }
}
