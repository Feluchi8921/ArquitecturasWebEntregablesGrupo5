package com.example.services;

import com.example.dto.ParadaDto;
import com.example.entities.Parada;
import com.example.mappers.ParadaMapper;
import com.example.repository.ParadaRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ParadaService {

    @Autowired
    ParadaRepository paradaRepository;
    @Autowired
    ParadaMapper paradaMapper;

    @Transactional
    public List<ParadaDto> findAll() {
        return this.paradaRepository.findAll()
                .stream().map(ParadaDto::new).toList();
    }

    @Transactional
    public ParadaDto findById(Long id){
        return this.paradaRepository.findById(id)
                .map(ParadaDto::new)
                .orElseThrow(()-> new RuntimeException("Parada con ID " + id + " no encontrado"));
    }

    @Transactional
    public ParadaDto save(ParadaDto paradaDto) {
        if (paradaDto.getNombre().isEmpty() || paradaDto.getLatitud() == 0.0  || paradaDto.getLongitud() == 0.0 || paradaDto.getCantMaxMonopatin() == null ) {
            throw new RuntimeException("Todos los campos son requeridos para crear un administrador.");
        }

        // Convertir el DTO a entidad Admin
        Parada nuevoAdmin = paradaMapper.toParada(paradaDto);

        // Si pasa las validaciones, guardar el estudiante en base de datos
        Parada guardado = paradaRepository.save(nuevoAdmin);

        return paradaMapper.toParadaDto(guardado);
    }

    public ParadaDto update(Long id, ParadaDto paradaDto) {
        if (paradaDto.getNombre().isEmpty() || paradaDto.getLatitud() == 0.0  || paradaDto.getLongitud() == 0.0 || paradaDto.getCantMaxMonopatin() == null ) {
            throw new RuntimeException("Todos los campos son requeridos para modificar un parada.");
        }

        // Buscar la parada por ID
        Parada paradaExistente = paradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parada con ID " + id + " no encontrado"));

        // Actualizar los campos de la parada con los datos del DTO
        paradaExistente.setNombre(paradaDto.getNombre());
        paradaExistente.setLatitud(paradaDto.getLatitud());
        paradaExistente.setCantMaxMonopatin(paradaDto.getCantMaxMonopatin());

        // Guardar los cambios
        paradaRepository.save(paradaExistente);

        return paradaMapper.toParadaDto(paradaExistente);
    }

    public ParadaDto delete(Long id) {
        Parada paradaExistente = paradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parada con ID " + id + " no encontrado"));

        // Eliminar el estudiante
        paradaRepository.delete(paradaExistente);

        // Devolver el admin eliminado
        return paradaMapper.toParadaDto(paradaExistente);
    }

}
