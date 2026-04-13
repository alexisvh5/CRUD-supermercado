package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.DetalleVentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademy.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademy.PruebaTecSupermercado.model.DetalleVenta;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademy.PruebaTecSupermercado.model.Venta;
import com.todocodeacademy.PruebaTecSupermercado.reposotry.IProductoRepository;
import com.todocodeacademy.PruebaTecSupermercado.reposotry.ISucursalRepository;
import com.todocodeacademy.PruebaTecSupermercado.reposotry.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventaRepository;
    @Autowired
    private IProductoRepository productoRepository;
    @Autowired
    private ISucursalRepository sucursalRepository;

    @Override
    public List<VentaDTO> traerVentas() {
        List<Venta>ventas = ventaRepository.findAll();
        List<VentaDTO>ventasDTO = new ArrayList<>();

        //la hacemos asi para ver variantes al mapper
        VentaDTO dto;
    for(Venta v : ventas){
        dto = Mapper.toDTO(v);
        ventasDTO.add(dto);
    }

        return ventasDTO;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        //primero chequear que ese ventaDto no sea null
        //que haya una sucursal en ese dto
        // que haya al menos unn detalle
        if(ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if(ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if(ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty()) throw new RuntimeException("Debe incluir al menos  un producto");

        Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc ==null) throw new RuntimeException("la sucursal no se encontro");

//venta--- ahi nos fijamoos si por lo menos tiene una venta
Venta vent = new Venta();
vent.setFecha(ventaDto.getFecha());
vent.setEstado(ventaDto.getEstado());
vent.setSucursal(suc);
vent.setTotal(ventaDto.getTotal());

        List<DetalleVentaDTO>detallesVentasDto = ventaDto.getDetalle();
        List<DetalleVenta>detallesVentas = new ArrayList<>();
        Double totalCalculado=0.0;

        for (DetalleVentaDTO detDTO : detallesVentasDto){
            //busco producto por id
            Producto p = productoRepository.findById(detDTO.getIdProducto()).orElse(null);
            if (p==null){throw new RuntimeException("producto no encntorado "+ detDTO.getNombreProd());}
            if (p.getCantidad() < detDTO.getCantProd()){throw new RuntimeException("no hay stock para este producto: "+ detDTO.getNombreProd());}
            //descuento la cantidad que fue aceptada al stock
            p.setCantidad(p.getCantidad()-detDTO.getCantProd());
            //creo detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setVenta(vent);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detallesVentas.add(detalleVent);
        }
//seteamos la lista de detalles venta
vent.setDetalle(detallesVentas);

        ventaRepository.save(vent);
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDto) {
        //buscar si la venta existe para actualizarla
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDto.getFecha()!=null) {
            v.setFecha(ventaDto.getFecha());
        }
        if(ventaDto.getEstado()!=null) {
            v.setEstado(ventaDto.getEstado());
        }

        if (ventaDto.getTotal()!=null) {
            v.setTotal(ventaDto.getTotal());
        }

        if (ventaDto.getIdSucursal()!=null) {
            Sucursal suc = sucursalRepository.findById(ventaDto.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepository.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarventa(Long id) {
        Venta v = ventaRepository.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepository.delete(v);

    }
}
