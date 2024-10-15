package org.example.integrador_3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
public class Inscripcion{
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idInscripcion;

    @Column
    private LocalDate fechaInscripcion;

    @Column
    private LocalDate fechaEgreso;

    @Column
    private int antiguedad;

    @Column
    private boolean graduado;

    @ManyToOne
    private Carrera carrera; // Relación N:1 (Inscripción-Carrera)

    @ManyToOne
    private Estudiante estudiante; // Relación N:1 (Inscripción-Alumno)

    // Constructores
    public Inscripcion() {}

    public Inscripcion(Carrera carrera, Estudiante estudiante, LocalDate fechaInscripcion, LocalDate fechaEgreso, int antiguedad, boolean graduado) {
        this.carrera = carrera;
        this.estudiante = estudiante;
        this.fechaInscripcion = fechaInscripcion;
        this.fechaEgreso = fechaEgreso;
        this.antiguedad = antiguedad;
        this.graduado = graduado;
    }
}
