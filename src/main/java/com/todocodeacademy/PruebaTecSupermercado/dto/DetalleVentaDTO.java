package com.todocodeacademy.PruebaTecSupermercado.dto;

import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.model.Venta;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetalleVentaDTO {

    private Long id; // porque no id de detalleVenta ?  porque buscar un producto directamente ?
    private Long idProducto;
    private String nombreProd;
    private Integer cantProd;
    private Double precio; // precio al momento de la venta-- para q no dependa del cambio de precios de prodcutos
    private Double subtotal; //necesario a fut para el total de la venta
}
