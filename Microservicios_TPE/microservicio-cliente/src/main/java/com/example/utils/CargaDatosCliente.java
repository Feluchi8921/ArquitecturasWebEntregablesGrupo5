package com.example.utils;

import com.example.entities.Cuenta;
import com.example.entities.Usuario;
import com.example.repository.CuentaRepository;
import com.example.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@Slf4j
public class CargaDatosCliente {

    @Bean
    CommandLineRunner cargarDatos(@Qualifier("usuarioRepository") UsuarioRepository usuarioRepository,
                                  @Qualifier("cuentaRepository") CuentaRepository cuentaRepository) {
        return args -> {
            // Guardar un nuevo Usuario
            Usuario usuario = new Usuario(1L, "armando1956", "Armando", "Paredes", "12345678", "armandoparedes@mail.com");
            log.info("Preloading " + usuarioRepository.save(usuario));

            // Guardar una nueva Cuenta
            Cuenta cuenta = new Cuenta(1L, 10500.0, LocalDate.of(2020, 3, 15), 9500.0, true);
            log.info("Preloading " + cuentaRepository.save(cuenta));
        };
    }

}
