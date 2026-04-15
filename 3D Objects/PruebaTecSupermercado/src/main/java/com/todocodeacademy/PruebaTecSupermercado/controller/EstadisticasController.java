package com.todocodeacademy.PruebaTecSupermercado.controller;

import com.todocodeacademy.PruebaTecSupermercado.dto.ProductoDTO;
import com.todocodeacademy.PruebaTecSupermercado.service.IProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/estadisticas/producto-mas-vendido")
public class EstadisticasController {
@Autowired
    private IProductoService prodServ;

@GetMapping
    public ResponseEntity<ProductoDTO> productoMasVendido(){
    return ResponseEntity.ok(prodServ.buscarProductoMasVendido());
}
}
