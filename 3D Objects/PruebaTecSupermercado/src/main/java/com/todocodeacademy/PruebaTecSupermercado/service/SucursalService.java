package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.SucursalDTO;
import com.todocodeacademy.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademy.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import com.todocodeacademy.PruebaTecSupermercado.reposotry.ISucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService implements ISucursalService{

    @Autowired
    private ISucursalRepository sucursalRepo;

    @Override
    public List<SucursalDTO> traerSucursales() {
        return sucursalRepo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public SucursalDTO crearSucursal(SucursalDTO sucursalDto) {
        Sucursal suc= Sucursal.builder()
                .nombre(sucursalDto.getNombre())
                .direccion(sucursalDto.getDireccion())
                .build();

        return Mapper.toDTO(sucursalRepo.save(suc));
    }

    @Override
    public SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto) {
        Sucursal s = sucursalRepo.findById(id).orElseThrow(()->new NotFoundException("no se encontró el producto para acutalizar"));

        s.setNombre(sucursalDto.getNombre());
        s.setDireccion(sucursalDto.getDireccion());

        return Mapper.toDTO(sucursalRepo.save(s));
    }

    @Override
    public void eliminarSucursal(Long id) {
        Sucursal s = sucursalRepo.findById(id).orElseThrow(()->new NotFoundException("no se encontró el producto para eliminar"));
        sucursalRepo.deleteById(id);

    }
}
