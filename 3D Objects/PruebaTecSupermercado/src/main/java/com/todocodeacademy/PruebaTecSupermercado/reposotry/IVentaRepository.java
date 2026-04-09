package com.todocodeacademy.PruebaTecSupermercado.reposotry;

import com.todocodeacademy.PruebaTecSupermercado.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
}
