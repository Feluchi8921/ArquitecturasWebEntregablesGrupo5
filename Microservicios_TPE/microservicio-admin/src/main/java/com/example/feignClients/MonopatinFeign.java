package com.example.feignClients;

import com.example.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "microservicio-monopatin")
public interface MonopatinFeign {

    // Agrega un monopatín
    @PostMapping("api/monopatines/")
    Monopatin createMonopatin(@RequestBody Monopatin monopatin);

    // Modifica un monopatín
    @PutMapping("api/monopatines/id/{id}")
    Monopatin updateMonopatin(@RequestBody Monopatin monopatin, @PathVariable Long id);

    // Elimina un monopatín
    @DeleteMapping("api/monopatines/id/{id}")
    Monopatin deleteMonopatin(@PathVariable("id") Long id);

    // 3. e) Devuelve la cantidad de monopatines según su estado (disponibles y no disponibles)
    @GetMapping("api/monopatines/cantidad-operacion")
    int getCantidadEnOperacion();

    @GetMapping("api/monopatines/cantidad-mantenimiento")
    int getCantidadEnMantenimiento();

}
