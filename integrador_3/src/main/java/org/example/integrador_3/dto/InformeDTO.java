package org.example.integrador_3.dto;

import lombok.Data;

@Data
public class InformeDTO {
	private String nombreCarrera;
	private int anio;
	private long cantInscriptos;
	private long cantGraduados;

	public InformeDTO() {}

	public InformeDTO(String nombreCarrera, int anio, long cantInscriptos, long cantGraduados) {
		this.nombreCarrera = nombreCarrera;
		this.anio = anio;
		this.cantInscriptos = cantInscriptos;
		this.cantGraduados = cantGraduados;
	}

	@Override
	public String toString() {
		return "Carrera: " + nombreCarrera + " | " +
				"Fecha de egreso: " + anio + " | " +
				"Cantidad de inscriptos: " + cantInscriptos + " | " +
				"Cantidad de graduados: " + cantGraduados;
	}
}