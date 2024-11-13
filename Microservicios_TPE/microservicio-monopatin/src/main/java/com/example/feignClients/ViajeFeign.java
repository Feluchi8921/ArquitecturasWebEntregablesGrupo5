package com.example.feignClients;

import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;

@FeignClient(name = "microservicio-viaje")
public interface ViajeFeign {

}
