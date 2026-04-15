package com.todocodeacademy.PruebaTecSupermercado.reposotry;

import com.todocodeacademy.PruebaTecSupermercado.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository extends JpaRepository<Producto, Long> {
}
