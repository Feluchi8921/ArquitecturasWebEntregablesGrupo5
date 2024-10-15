package org.example.integrador_3.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class Estudiante implements Serializable {
    // Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long idEstudiante;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private int edad;

    @Column
    private String genero;

    @Column(unique = true)
    private int dni;

    @Column
    private String ciudad;

    @Column(unique = true)
    private int nroLibreta;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY) // Relación 1:N
    private List<Inscripcion> inscripciones;

    // Constructores
    public Estudiante() {}

    public Estudiante(String nombre, String apellido, int edad, String genero, int dni, String ciudad, int nroLibreta) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.dni = dni;
        this.ciudad = ciudad;
        this.nroLibreta = nroLibreta;
    }
}