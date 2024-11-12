package com.example.services;

import com.example.dto.TarifaDto;
import com.example.dto.ViajeDto;
import com.example.entities.Tarifa;
import com.example.entities.Viaje;
import com.example.mappers.ViajeMapper;
import com.example.repository.ViajeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ViajeService {

    @Autowired
    ViajeRepository viajeRepository;
    @Autowired
    ViajeMapper viajeMapper;

    @Transactional
    public List<ViajeDto> findAll() {
        return this.viajeRepository.findAll()
                .stream().map(ViajeDto::new).toList();
    }

    @Transactional
    public ViajeDto findById(Long id){
        return this.viajeRepository.findById(id)
                .map(ViajeDto::new)
                .orElseThrow(()-> new RuntimeException("Viaje con ID " + id + " no encontrado."));
    }

    @Transactional
    public ViajeDto save(ViajeDto viajeDto) {
        // Convierte LocalDateTime a String para JSON
        String inicioStr = viajeDto.getInicio().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String finStr = viajeDto.getFin().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        // Crea un nuevo Viaje con el nuevo formato
        Viaje viaje = new Viaje();
        viaje.setInicio(LocalDateTime.parse(inicioStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        viaje.setFin(LocalDateTime.parse(finStr, DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        viaje.setKmRecorridos(viajeDto.getKmRecorridos());
        viaje.setMinPausa(viajeDto.getMinPausa());
        viaje.setMaxTiempoPausa(viajeDto.getMaxTiempoPausa());

        // Se verifica fecha de actualización de tarifas
        if (LocalDate.now().isAfter(Tarifa.getFechaActualizacion())) {
            // Se actualizan tarifas en clase Tarifa
            Tarifa.setTarifaNormal(Tarifa.getNuevaTarifaNormal());
            Tarifa.setTarifaExtra(Tarifa.getNuevaTarifaExtra());
            log.info("--- Se actualizaron las tarifas ---");
        }

        // Se establecen tarifas en el viaje
        viaje.setTarifaNormal(Tarifa.getTarifaNormal());
        viaje.setTarifaExtra(Tarifa.getTarifaExtra());

        // Guarda el viaje en la base de datos
        Viaje savedViaje = viajeRepository.save(viaje);

        // Convierte el Viaje guaradado a ViajeDto
        return viajeMapper.toViajeDto(savedViaje);
    }

    public ViajeDto update(Long id, ViajeDto viajeDto) {
        if (viajeDto.getInicio() == null || viajeDto.getFin() == null || viajeDto.getKmRecorridos() == null ||
                viajeDto.getMinPausa() == null || viajeDto.getMaxTiempoPausa() == null) {
            throw new RuntimeException("Todos los campos son requeridos para modificar un viaje.");
        }

        // Buscar el admin por ID
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje con ID " + id + " no encontrado."));

        // Actualizar los campos del admin con los datos del DTO
        viajeExistente.setInicio(viajeDto.getInicio());
        viajeExistente.setFin(viajeDto.getFin());
        viajeExistente.setKmRecorridos(viajeDto.getKmRecorridos());
        viajeExistente.setMinPausa(viajeDto.getMinPausa());
        viajeExistente.setMaxTiempoPausa(viajeDto.getMaxTiempoPausa());

        // Guardar los cambios
        viajeRepository.save(viajeExistente);

        return viajeMapper.toViajeDto(viajeExistente);
    }

    public ViajeDto delete(Long id) {
        Viaje viajeExistente = viajeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viaje con ID " + id + " no encontrado."));

        // Eliminar el estudiante
        viajeRepository.delete(viajeExistente);

        // Devolver el admin eliminado
        return viajeMapper.toViajeDto(viajeExistente);
    }

    public void modificarTarifaNormal(TarifaDto tarifaDto) {
        Tarifa.setNuevaTarifaNormal(tarifaDto.getNuevoValor());
        Tarifa.setFechaActualizacion(tarifaDto.getFechaActualizacion());
    }

    public void modificarTarifaExtra(TarifaDto tarifaDto) {
        Tarifa.setNuevaTarifaExtra(tarifaDto.getNuevoValor());
        Tarifa.setFechaActualizacion(tarifaDto.getFechaActualizacion());
    }

}
