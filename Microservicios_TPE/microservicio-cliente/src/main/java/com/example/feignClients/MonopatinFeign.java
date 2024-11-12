package com.example.feignClients;

import com.example.model.Monopatin;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "microservicio-monopatin")
public interface MonopatinFeign {

    // 3. g) Cantidad de monopatines disponibles en una zona especificada.
    @GetMapping("/api/monopatines/cercanos")
    List<Monopatin> obtenerMonopatinesCercanos(
            @RequestParam("latitud") Double latitud,
            @RequestParam("longitud") Double longitud
    );

}
