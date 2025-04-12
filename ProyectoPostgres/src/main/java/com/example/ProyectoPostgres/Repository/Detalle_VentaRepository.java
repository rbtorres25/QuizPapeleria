package com.example.ProyectoPostgres.Repository;

import com.example.ProyectoPostgres.Model.Detalle_Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Detalle_VentaRepository extends JpaRepository<Detalle_Venta, Integer> {
}