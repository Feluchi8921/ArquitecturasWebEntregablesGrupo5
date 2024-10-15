package org.example.integrador_3;

import jakarta.annotation.PostConstruct;
import org.example.integrador_3.utils.CargaBaseDeDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Integrador3Application {
    @Autowired
    private CargaBaseDeDatos cargaBaseDeDatos;

    public static void main(String[] args) {
        SpringApplication.run(Integrador3Application.class, args);
    }

    @PostConstruct
    public void init() throws IOException {
        cargaBaseDeDatos.cargarDatosDesdeCSV();
    }
}
