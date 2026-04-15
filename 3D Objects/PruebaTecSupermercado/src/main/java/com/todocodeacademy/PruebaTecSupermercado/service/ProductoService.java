package com.todocodeacademy.PruebaTecSupermercado.service;
import com.todocodeacademy.PruebaTecSupermercado.dto.VentaDTO;
import com.todocodeacademy.PruebaTecSupermercado.model.DetalleVenta;
import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import com.todocodeacademy.PruebaTecSupermercado.exception.NotFoundException;
import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademy.PruebaTecSupermercado.mapper.Mapper;
import com.todocodeacademy.PruebaTecSupermercado.model.Venta;
import com.todocodeacademy.PruebaTecSupermercado.reposotry.IProductoRepository;
import com.todocodeacademy.PruebaTecSupermercado.reposotry.IVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepo;
    @Autowired
    private IVentaRepository ventaRepository;

    @Override
    public List<ProductoDTO> traerProductos() {
        return productoRepo.findAll().stream().map(Mapper::toDTO).toList();// se puede con mapeo o con programacion funcional--streams
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDto) {
        Producto prod = Producto.builder().nombre(productoDto.getNombre()).categoria(productoDto.getCategoria()).precio(productoDto.getPrecio()).cantidad(productoDto.getCantidad()).build();
        return Mapper.toDTO(productoRepo.save(prod));// esto es solo pa mostrar --en teoria solo se hace el save y listo
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        Producto p = productoRepo.findById(id).orElseThrow(()->new NotFoundException("no se encontró el producto"));

        p.setNombre(productoDto.getNombre());
        p.setCategoria(productoDto.getCategoria());
        p.setCantidad(productoDto.getCantidad());
        p.setPrecio(productoDto.getPrecio());

        return Mapper.toDTO(productoRepo.save(p));
    }

    @Override
    public void eliminarProducto(Long id) {
        if (!productoRepo.existsById(id)) {
            throw new NotFoundException("producto no encontrado para eliminar");
        }
        productoRepo.deleteById(id);
    }

    @Override
    public ProductoDTO buscarProductoMasVendido() {

     /*   List<Venta>listaDeVentas = ventaRepository.findAll();
        List<DetalleVenta>listaDeTodosLosDetallesDeVentas= new ArrayList<>();

        for(Venta v : listaDeVentas){
            for(DetalleVenta det : v.getDetalle()){
                listaDeTodosLosDetallesDeVentas.add(det);
            }
        }
        DetalleVenta detalleDeProductoMasVendido = listaDeTodosLosDetallesDeVentas.stream().max(Comparator.comparingDouble(DetalleVenta::getCantProd)).orElse(null);

        return Mapper.toDTO(productoRepo.findById(detalleDeProductoMasVendido.getProd().getId()).orElse(null));*/

        List<Venta> listaDeVentas = ventaRepository.findAll();

        Map<Producto, Integer> acumulado = listaDeVentas.stream()
                .flatMap(v -> v.getDetalle().stream())
                .collect(Collectors.groupingBy(
                        DetalleVenta::getProd,
                        Collectors.summingInt(DetalleVenta::getCantProd)
                ));

        Producto productoMasVendido = acumulado.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);

        return Mapper.toDTO(productoMasVendido);
    }

}
