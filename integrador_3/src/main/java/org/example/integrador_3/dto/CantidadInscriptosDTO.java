package org.example.integrador_3.dto;

import lombok.Data;

@Data
public class CantidadInscriptosDTO {
    private String nombreCarrera;
    private Long cantInscriptos;

    public CantidadInscriptosDTO() {}

    public CantidadInscriptosDTO(String nombreCarrera, Long cantInscriptos) {
        this.nombreCarrera = nombreCarrera;
        this.cantInscriptos = cantInscriptos;
    }

    @Override
    public String toString() {
        return "Carrera: " + nombreCarrera + " | " +
                "Cantidad de inscriptos: " + cantInscriptos;
    }
}
