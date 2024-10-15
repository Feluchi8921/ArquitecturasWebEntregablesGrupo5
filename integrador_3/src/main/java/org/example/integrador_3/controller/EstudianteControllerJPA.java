package org.example.integrador_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.integrador_3.dto.EstudianteDTO;
import org.example.integrador_3.model.Estudiante;
import org.example.integrador_3.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController // Indica que esta clase es un controlador REST
@RequestMapping("estudiantes") // Mapea todas las solicitudes a la ruta base "/estudiantes"
public class EstudianteControllerJPA {
    @Autowired
    private final EstudianteService estudianteService; // Se inyecta el servicio de estudiantes

    public EstudianteControllerJPA(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    // Obtiene una lista de todos los estudiantes
    @GetMapping("/")
    public List<EstudianteDTO> getAll() throws Exception {
        return estudianteService.getAll();
    }

    // Obtiene un estudiante por ID
    @GetMapping("/{id}")
    public EstudianteDTO getById(@PathVariable long id) throws Exception {
        return estudianteService.getById(id);
    }

    // d) Obtiene un estudiante por su número de libreta
    @GetMapping("/libreta/{nroLibreta}")
    public EstudianteDTO getByNroLibreta(@PathVariable int nroLibreta) throws Exception {
        return estudianteService.getByNroLibreta(nroLibreta);
    }

    // c) Obtiene una lista de estudiantes por apellido
    @GetMapping("/apellido/{apellido}")
    public List<EstudianteDTO> getAllByApellido(@PathVariable String apellido) {
        return estudianteService.getAllByApellido(apellido);
    }

    // e) Obtiene una lista de estudiantes por género
    @GetMapping("/genero/{genero}")
    public List<EstudianteDTO> getAllByGenero(@PathVariable String genero) {
        return estudianteService.getAllByGenero(genero);
    }

    // g) Obtiene los estudiantes de una carrera determinada filtrado por ciudad
    @GetMapping("/carrera/{carrera}/ciudad/{ciudad}")
    public List<EstudianteDTO> getAllByCarreraCiudad(@PathVariable String carrera,
                                                     @PathVariable String ciudad) {
        return estudianteService.getAllByCarreraCiudad(carrera, ciudad);
    }

    // a) Crea un nuevo estudiante
    @PostMapping("/")
    public EstudianteDTO save(@RequestBody Estudiante e) throws Exception {
        return estudianteService.save(e); // Guarda la nueva persona en la base de datos
    }

    // Elimina un estudiante por ID
    @DeleteMapping("/{id}")
    public EstudianteDTO delete(@PathVariable Long id) throws Exception {
        return estudianteService.delete(id); // Elimina el estudiante con el ID especificado
    }
}
