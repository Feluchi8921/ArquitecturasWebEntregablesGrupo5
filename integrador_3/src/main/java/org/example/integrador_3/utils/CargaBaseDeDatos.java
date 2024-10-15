package org.example.integrador_3.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.example.integrador_3.model.Carrera;
import org.example.integrador_3.model.Estudiante;
import org.example.integrador_3.model.Inscripcion;
import org.example.integrador_3.repository.EstudianteRepository;
import org.example.integrador_3.repository.CarreraRepository;
import org.example.integrador_3.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class CargaBaseDeDatos {
    private final EstudianteRepository er;
    private final CarreraRepository cr;
    private final InscripcionRepository ir;

    private List<Estudiante> estudiantes;
    private List<Carrera> carreras;

    @Autowired
    public CargaBaseDeDatos(EstudianteRepository er, CarreraRepository cr, InscripcionRepository ir) {
        this.er = er;
        this.cr = cr;
        this.ir = ir;
        this.estudiantes = new ArrayList<>();
        this.carreras = new ArrayList<>();
    }

    public void cargarDatosDesdeCSV() throws IOException {
        cargarEstudiantesCSV();
        cargarCarrerasCSV();
        cargarInscripcionesCSV();
    }

    private void cargarEstudiantesCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/resources/csv/alumnos.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Estudiante e = new Estudiante();
                e.setNombre(csvRecord.get("nombre"));
                e.setApellido(csvRecord.get("apellido"));
                e.setEdad(Integer.parseInt(csvRecord.get("edad")));
                e.setGenero(csvRecord.get("genero"));
                e.setDni(Integer.parseInt(csvRecord.get("dni")));
                e.setCiudad(csvRecord.get("ciudad"));
                e.setNroLibreta(Integer.parseInt(csvRecord.get("nroLibreta")));
                estudiantes.add(e);
                er.save(e);
            }
        }
    }

    private void cargarCarrerasCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/resources/csv/carreras.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Carrera c = new Carrera();
                c.setNombreCarrera(csvRecord.get("nombreCarrera"));
                carreras.add(c);
                cr.save(c);
            }
        }
    }

    private void cargarInscripcionesCSV() throws IOException {
        File archivoCSV = ResourceUtils.getFile("src/main/resources/csv/inscripciones.csv");

        try (FileReader reader = new FileReader(archivoCSV);
             CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader)) {
            for (CSVRecord csvRecord : csvParser) {
                Inscripcion i = new Inscripcion();
                Carrera c = getCarreraById(Integer.parseInt(csvRecord.get("idCarrera")));
                i.setCarrera(c);
                Estudiante e = getEstudianteById(Integer.parseInt(csvRecord.get("idEstudiante")));
                i.setEstudiante(e);
                i.setFechaInscripcion(LocalDate.parse(csvRecord.get("fechaInscripcion")));
                String fechaEgreso = csvRecord.get("fechaEgreso");
                if (fechaEgreso.isEmpty()) {
                    i.setFechaEgreso(null);
                } else {
                    i.setFechaEgreso(LocalDate.parse(fechaEgreso));
                }
                i.setAntiguedad(Integer.parseInt(csvRecord.get("antiguedad")));
                i.setGraduado(Boolean.parseBoolean(csvRecord.get("graduado")));
                ir.save(i);
            }
        }
    }

    private Carrera getCarreraById(int id) {
        for (Carrera c : carreras) {
            if (c.getIdCarrera() == id) {
                return c;
            }
        }
        return null;
    }

    private Estudiante getEstudianteById(int id) {
        for (Estudiante e : estudiantes) {
            if (e.getIdEstudiante() == id) {
                return e;
            }
        }
        return null;
    }
}
