package com.todocodeacademy.PruebaTecSupermercado.mapper;

import com.todocodeacademy.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademy.PruebaTecSupermercado.model.Venta;

import java.util.stream.Collectors;

public class Mapper {

    public static ProductoDTO toDTO(Producto p){
        if(p == null) return null;
     return ProductoDTO.builder().id(p.getId()).nombre(p.getNombre()).categoria(p.getCategoria()).precio(p.getPrecio()).cantidad(p.getCantidad()).build();

    }
    public static SucursalDTO toDTO(Sucursal s){
        if(s == null) return null;
        return SucursalDTO.builder().id(s.getId()).nombre(s.getNombre()).direccion(s.getDireccion()).build();

    }

    public static VentaDTO toDTO(Venta v){
        if(v == null) return null;

        var detalleV = v.getDetalle().stream().map( // no sabermos q tipo de dato puede llegar a la variable
detalleVenta -> DetalleVentaDTO.builder()
        .id(detalleVenta.getId())
        .idProducto(detalleVenta.getProd().getId())
        .nombreProd(detalleVenta.getProd().getNombre())
        .cantProd(detalleVenta.getCantProd())
        .precio(detalleVenta.getPrecio())
        .subtotal(detalleVenta.getPrecio() * detalleVenta.getCantProd())
        .build()
        ).collect(Collectors.toList());

        var total = detalleV.stream().map(DetalleVentaDTO::getSubtotal).reduce(0.0, Double::sum );

        return VentaDTO.builder().id(v.getId()).fecha(v.getFecha()).idSucursal(v.getSucursal().getId()).estado(v.getEstado()).detalle(detalleV).total(total).build();

    }

}
