package com.todocodeacademy.PruebaTecSupermercado.service;

import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IProductoService {

List<ProductoDTO>traerProductos();
ProductoDTO crearProducto(ProductoDTO prdDTO);
ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);
void eliminarProducto(Long id);
ProductoDTO buscarProductoMasVendido();

}
