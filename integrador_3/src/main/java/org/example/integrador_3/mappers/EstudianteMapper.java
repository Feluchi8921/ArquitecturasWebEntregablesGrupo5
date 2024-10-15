package org.example.integrador_3.mappers;
import org.example.integrador_3.dto.EstudianteDTO;
import org.example.integrador_3.model.Estudiante;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EstudianteMapper {
    //Convierte un estudiante a estudianteDTO
    EstudianteDTO toEstudianteDTO(Estudiante estudiante);

    //Convierte una lista de estudiante a una lista de estudiantesDTO
    List<EstudianteDTO> toEstudiantesDTO(List<Estudiante> estudiante);
}
