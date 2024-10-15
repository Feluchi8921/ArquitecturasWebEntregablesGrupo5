package org.example.integrador_3.controller;

import org.example.integrador_3.dto.CantidadInscriptosDTO;
import org.example.integrador_3.dto.InformeDTO;
import org.example.integrador_3.model.Estudiante;
import org.example.integrador_3.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inscripciones")
public class InscripcionControllerJPA {
    @Autowired
    private InscripcionService inscripcionService;

    // b) Matricula un estudiante en una carrera
    @PostMapping("/{idCarrera}")
    public ResponseEntity<Void> save(@PathVariable long idCarrera, @RequestBody Estudiante e) {
        try {
            inscripcionService.save(idCarrera, e);
            return ResponseEntity.status(HttpStatus.CREATED).build();  // 201 Created si se matricula con éxito
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();  // 400 Bad Request si los datos son inválidos
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error para errores genéricos
        }
    }

    // f) Obtiene una lista de carreras ordenadas por cantidad de inscriptos
    @GetMapping("/inscriptos-carreras")
    public ResponseEntity<List<CantidadInscriptosDTO>> getAllByCantInscriptos() {
        try {
            List<CantidadInscriptosDTO> result = inscripcionService.getAllByCantInscriptos();
            if (result.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 No Content si no hay carreras
            } else {
                return ResponseEntity.ok(result);  // 200 OK con la lista de carreras
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error si ocurre un error
        }
    }

    // h) Genera un informe de las carreras con inscriptos y egresados por año
    @GetMapping("/informe")
    public ResponseEntity<List<InformeDTO>> generarInforme() {
        try {
            List<InformeDTO> informe = inscripcionService.generarInforme();
            if (informe.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();  // 204 No Content si no hay datos
            } else {
                return ResponseEntity.ok(informe);  // 200 OK con el informe
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();  // 500 Internal Server Error si ocurre un error
        }
    }
}
