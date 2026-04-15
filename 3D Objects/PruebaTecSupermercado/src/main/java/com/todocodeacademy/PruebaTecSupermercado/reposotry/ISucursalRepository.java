package com.todocodeacademy.PruebaTecSupermercado.reposotry;

import com.todocodeacademy.PruebaTecSupermercado.model.Sucursal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISucursalRepository extends JpaRepository<Sucursal, Long> {
}
