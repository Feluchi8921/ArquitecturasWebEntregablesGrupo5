package org.example.integrador_3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.integrador_3.dto.CarreraDTO;
import org.example.integrador_3.model.Carrera;
import org.example.integrador_3.service.CarreraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("carreras")
public class CarreraControllerJPA {
    @Autowired
    private final CarreraService carreraService;

    public CarreraControllerJPA(CarreraService carreraService) {
        this.carreraService = carreraService;
    }

    // Obtiene una lista de todas las carreras
    @GetMapping("/")
    public ResponseEntity<List<CarreraDTO>> getAll() {
        try {
            List<CarreraDTO> carreras = carreraService.getAll();
            if (carreras.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content si no hay carreras
            }
            return ResponseEntity.ok(carreras); // 200 OK con la lista de carreras
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Internal Server Error si falla
        }
    }

    // Obtiene una carrera por ID
    @GetMapping("/{id}")
    public ResponseEntity<CarreraDTO> getById(@PathVariable Long id) {
        try {
            CarreraDTO carrera = carreraService.getById(id);
            return ResponseEntity.ok(carrera); // 200 OK si la carrera es encontrada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si no se encuentra la carrera
        }
    }

    // Crea una nueva carrera
    @PostMapping("/")
    public ResponseEntity<CarreraDTO> save(@RequestBody Carrera c) {
        try {
            CarreraDTO nuevaCarrera = carreraService.save(c);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCarrera); // 201 Created si la carrera es creada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // 400 Bad Request si los datos son inválidos
        }
    }

    // Elimina una carrera por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            carreraService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // 204 No Content si la carrera es eliminada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // 404 Not Found si no se encuentra la carrera
        }
    }
}
