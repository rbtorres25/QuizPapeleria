package com.example.ProyectoPostgres.Repository;

import com.example.ProyectoPostgres.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query; // <-- IMPORTANTE
import org.springframework.data.repository.query.Param; // <-- IMPORTANTE
import org.springframework.stereotype.Repository;

import java.util.List; // <-- También importante si no lo tienes

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    @Query(value = "SELECT * FROM producto WHERE id_proveedor = :idProveedor", nativeQuery = true)
    List<Producto> findByProveedor(@Param("idProveedor") int idProveedor);
}