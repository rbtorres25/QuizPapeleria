package com.example.ProyectoPostgres.Controller;

import com.example.ProyectoPostgres.Model.Producto;
import com.example.ProyectoPostgres.Service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@CrossOrigin(origins = "*")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAll() {
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<List<Producto>> getProductosPorProveedor(@PathVariable int id) {
        List<Producto> productos = productoService.getProductosPorProveedor(id);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productos);
        }
    } // <- AQUÍ FALTABA ESTA LLAVE

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getById(@PathVariable int id) {
        Producto producto = productoService.findById(id);
        return producto != null ? ResponseEntity.ok(producto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        return ResponseEntity.ok(productoService.save(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> update(@PathVariable int id, @RequestBody Producto producto) {
        Producto existente = productoService.findById(id);
        if (existente != null) {
            producto.setId_producto(id);
            return ResponseEntity.ok(productoService.save(producto));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Producto existente = productoService.findById(id);
        if (existente != null) {
            productoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
