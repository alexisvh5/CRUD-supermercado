package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;

import java.util.List;

public interface ISucursalService {

    // metodos crud basicos
    List<SucursalDTO>traerSucursales();

    SucursalDTO crearSucursal(SucursalDTO sucursalDto); // para poder devolver datos, en vez de void

    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);

    void eliminarSucursal(Long id);

}
