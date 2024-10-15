package org.example.integrador_3.dto;

import lombok.Data;

@Data
public class CarreraDTO {
    private String nombreCarrera;

    public CarreraDTO(String nombreCarrera) {
        this.nombreCarrera = nombreCarrera;
    }

    @Override
    public String toString() {
        return "Carrera: " + nombreCarrera;
    }
}
