package com.example.ProyectoPostgres.Controller;

import com.example.ProyectoPostgres.Model.Proveedor;
import com.example.ProyectoPostgres.Service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/proveedores")
@CrossOrigin(origins = "*")
public class ProveedorController {
    @Autowired
    private ProveedorService proveedorService;

    @GetMapping
    public ResponseEntity<List<Proveedor>> getAll() {
        return ResponseEntity.ok(proveedorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> getById(@PathVariable int id) {
        Proveedor proveedor = proveedorService.findById(id);
        return proveedor != null ? ResponseEntity.ok(proveedor) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Proveedor> create(@RequestBody Proveedor proveedor) {
        return ResponseEntity.ok(proveedorService.save(proveedor));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Proveedor> update(@PathVariable int id, @RequestBody Proveedor proveedor) {
        Proveedor existente = proveedorService.findById(id);
        if (existente != null) {
            proveedor.setId_proveedor(id);
            return ResponseEntity.ok(proveedorService.save(proveedor));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Proveedor existente = proveedorService.findById(id);
        if (existente != null) {
            proveedorService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}