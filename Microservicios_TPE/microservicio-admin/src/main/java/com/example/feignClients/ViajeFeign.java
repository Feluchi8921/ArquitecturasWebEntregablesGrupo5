package com.example.feignClients;

import com.example.dto.TarifaRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservicio-viaje")
public interface ViajeFeign {

    // Modifica el valor de la tarifa normal
    @PutMapping("api/viajes/tarifa-normal/")
    void modificarTarifaNormal(@RequestBody TarifaRequestDto tarifaRequestDto);

    // Modifica el valor de la tarifa extra
    @PutMapping("api/viajes/tarifa-extra/")
    void modificarTarifaExtra(@RequestBody TarifaRequestDto tarifaRequestDto);

}
