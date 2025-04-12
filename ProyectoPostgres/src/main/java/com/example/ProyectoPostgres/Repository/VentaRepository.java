
package com.example.ProyectoPostgres.Repository;

import com.example.ProyectoPostgres.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado", nativeQuery = true)
    List<Venta> findVentasByEmpleado(@Param("idEmpleado") int idEmpleado);

    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente", nativeQuery = true)
    List<Venta> findVentasByEmpleadoAndCliente(@Param("idEmpleado") int idEmpleado, @Param("idCliente") int idCliente);
}
