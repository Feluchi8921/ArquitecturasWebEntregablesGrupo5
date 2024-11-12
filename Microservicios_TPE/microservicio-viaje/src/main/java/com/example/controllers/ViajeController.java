package com.example.controllers;

import com.example.dto.TarifaDto;
import com.example.dto.ViajeDto;
import com.example.entities.Tarifa;
import com.example.services.ViajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/viajes")
public class ViajeController {

    @Autowired
    ViajeService viajeService;

    @GetMapping("/")
    public List<ViajeDto> getAll() {
        return viajeService.findAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            ViajeDto viaje = viajeService.findById(id);
            return ResponseEntity.ok(viaje);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el viaje con ID " + id);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> create(@RequestBody ViajeDto viajeDto) {
        try {
            ViajeDto viajeDtoCreado = viajeService.save(viajeDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeDtoCreado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el viaje");
        }
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ViajeDto viaje) {
        try {
            ViajeDto viajeDtoModificado = viajeService.update(id, viaje);
            return ResponseEntity.status(HttpStatus.CREATED).body(viajeDtoModificado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo modificar el viaje");
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            ViajeDto viajeDtoEliminado = viajeService.delete(id);
            return ResponseEntity.ok(viajeDtoEliminado);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el viaje con ID " + id);
        }
    }

    @PutMapping("/tarifa-normal/")
    public ResponseEntity<?> modificarTarifaNormal(@RequestBody TarifaDto tarifaDto) {
        viajeService.modificarTarifaNormal(tarifaDto);
        return ResponseEntity.ok("Nuevo valor de tarifa normal: " + tarifaDto.getNuevoValor());
    }

    @PutMapping("/tarifa-extra/")
    public ResponseEntity<?> modificarTarifaExtra(@RequestBody TarifaDto tarifaDto) {
        viajeService.modificarTarifaExtra(tarifaDto);
        return ResponseEntity.ok("Nuevo valor de tarifa extra: " + tarifaDto.getNuevoValor());
    }

}
