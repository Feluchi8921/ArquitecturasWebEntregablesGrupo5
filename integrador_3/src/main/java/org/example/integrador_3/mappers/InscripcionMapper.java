package org.example.integrador_3.mappers;

import org.example.integrador_3.dto.CantidadInscriptosDTO;
import org.example.integrador_3.model.Carrera;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InscripcionMapper {
    List<CantidadInscriptosDTO> toCantInscriptosDTO(List<Carrera> carreras);
}
