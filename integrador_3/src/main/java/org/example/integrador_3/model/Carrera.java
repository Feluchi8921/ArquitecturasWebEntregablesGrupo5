package org.example.integrador_3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Carrera implements Serializable {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idCarrera;

    @Column
    private String nombreCarrera;

    @OneToMany(mappedBy = "carrera", fetch = FetchType.LAZY) // Relación 1:N
    private List<Inscripcion> estudiantes;

    // Constructores
    public Carrera() {}

    public Carrera(long idCarrera, String nombreCarrera) {
        this.idCarrera = idCarrera;
        this.nombreCarrera = nombreCarrera;
    }
}