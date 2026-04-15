package com.todocodeacademy.PruebaTecSupermercado.dto;

import com.todocodeacademy.PruebaTecSupermercado.model.DetalleVenta;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VentaDTO {
    private Long id;
    private LocalDate fecha;
    private String estado;
    private Double total;

    private Long idSucursal; // no necesito mostrar toda la sucursal
//lista de detalles..producto--cantidad--precio, etc uno abajo del otro
    private List<DetalleVentaDTO> detalle;
}
