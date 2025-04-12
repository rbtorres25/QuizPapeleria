package com.example.ProyectoPostgres.Service;

import com.example.ProyectoPostgres.Model.Producto;
import com.example.ProyectoPostgres.Repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para manejar operaciones relacionadas con la entidad Producto.
 */
@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    /**
     * Obtiene una lista de todos los productos.
     *
     * @return List<Producto> - lista de productos.
     */
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    /**
     * Busca un producto por su ID.
     *
     * @param id - ID del producto a buscar.
     * @return Producto - producto encontrado o null si no existe.
     */
    public Producto findById(int id) {
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * Guarda un nuevo producto o actualiza uno existente.
     *
     * @param producto - producto a guardar.
     * @return Producto - producto guardado.
     */
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Elimina un producto por su ID.
     *
     * @param id - ID del producto a eliminar.
     */
    public void deleteById(int id) {
        productoRepository.deleteById(id);
    }



    public List<Producto> getProductosPorProveedor(int idProveedor) {
        return productoRepository.findByProveedor(idProveedor);
    }

}