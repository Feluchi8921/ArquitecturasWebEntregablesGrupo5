package org.example.integrador_3.service;

import org.example.integrador_3.dto.CarreraDTO;
import org.example.integrador_3.mappers.CarreraMapper;
import org.example.integrador_3.mappers.InscripcionMapper;
import org.example.integrador_3.model.Carrera;
import org.example.integrador_3.repository.CarreraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("CarreraServicio")
public class CarreraService {
    @Autowired
    CarreraRepository carreraRepository;

    @Autowired
    CarreraMapper carreraMapper;

    @Autowired
    InscripcionMapper inscripcionMapper;

    public List<CarreraDTO> getAll() throws Exception {
        return carreraMapper.toCarrerasDTO(carreraRepository.findAll());
    }

    public CarreraDTO getById(Long id) throws Exception {
        Optional<Carrera> carrera = carreraRepository.findById(id);
        if (carrera.isEmpty()) {
            throw new Exception("No se encontró la carrera con ID: " + id);
        }
        return carreraMapper.toCarreraDTO(carrera.get());
    }

    public CarreraDTO save(Carrera c) throws Exception {
        Optional<Carrera> carrera = carreraRepository.findById(c.getIdCarrera());
        if (carrera.isPresent()) {
            throw new Exception("La carrera ya existe");
        }
        carreraRepository.save(c);
        return carreraMapper.toCarreraDTO(c);
    }

    public CarreraDTO delete(Long id) throws Exception {
        Optional<Carrera> carrera = carreraRepository.findById(id);
        if (carrera.isEmpty()) {
            throw new Exception("Carrera " + id + " no encontrada");
        }
        carreraRepository.delete(carrera.get());
        return carreraMapper.toCarreraDTO(carrera.get());
    }
}
