package com.example.feignClients;

import com.example.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "microservicio-monopatin")
public interface MonopatinFeign {

    // Establece un monopatín como "En mantenimiento"
    @PutMapping("api/monopatines/iniciar-mantenimiento/{id}")
    void iniciarMantenimiento(@PathVariable Long id);

    // Establece un monopatín como "Disponible"
    @PutMapping("api/monopatines/finalizar-mantenimiento/{id}")
    void finalizarMantenimiento(@PathVariable Long id);

    // 3. a) Genera reporte de monopatines para verificar mantenimiento.
    @GetMapping("api/monopatines/reporte")
    List<Monopatin> obtenerReporte(@RequestParam int kmMantenimiento);

}
