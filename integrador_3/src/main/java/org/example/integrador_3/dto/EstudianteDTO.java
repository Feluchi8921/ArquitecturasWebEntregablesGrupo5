package org.example.integrador_3.dto;

import lombok.Data;

@Data
public class EstudianteDTO {
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	private int dni;
	private String ciudad;
	private int nroLibreta;

	public EstudianteDTO() {}

	public EstudianteDTO(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int nroLibreta) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.edad = edad;
		this.genero = genero;
		this.dni = dni;
		this.ciudad = ciudad;
		this.nroLibreta = nroLibreta;
	}

	@Override
	public String toString() {
		return "Nombre: " + nombre + " | " +
				"Apellido: " + apellido + " | " +
				"Edad: " + edad + " | " +
				"Género: " + genero + " | " +
				"DNI: " + dni +
				"Ciudad: " + ciudad + " | " +
				"LU: " + nroLibreta;
	}
}
