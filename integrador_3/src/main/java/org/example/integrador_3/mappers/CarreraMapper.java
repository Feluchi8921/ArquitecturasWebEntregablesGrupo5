package org.example.integrador_3.mappers;

import org.example.integrador_3.dto.CarreraDTO;
import org.example.integrador_3.model.Carrera;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CarreraMapper {
    //Convierte una carrera a carreraDTO
    CarreraDTO toCarreraDTO(Carrera carrera);

    //Convierte una lista de carreras a una lista de carrerasDTO
    List<CarreraDTO> toCarrerasDTO(List<Carrera> carreras);
}
