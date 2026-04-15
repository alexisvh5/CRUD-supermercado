package com.todocodeacademy.PruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductoDTO {
    private Long id;
    private String nombre;
    private String categoria; // despues pasa a enum
    private Double precio;
    private Integer cantidad; /// para el stokc?
}
