package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonopatinConPausaDto {

    private Long id;
    private Double kmRecorridos;
    private Long tiempoPausa;

}
