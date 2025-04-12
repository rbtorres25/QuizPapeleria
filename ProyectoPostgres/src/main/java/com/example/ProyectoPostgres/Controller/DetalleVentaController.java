package com.example.ProyectoPostgres.Controller;

import com.example.ProyectoPostgres.Model.Detalle_Venta;
import com.example.ProyectoPostgres.Service.Detalle_VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalles")
@CrossOrigin(origins = "*")
public class DetalleVentaController {
    @Autowired
    private Detalle_VentaService detalleVentaService;

    @GetMapping
    public ResponseEntity<List<Detalle_Venta>> getAll() {
        return ResponseEntity.ok(detalleVentaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Detalle_Venta> getById(@PathVariable int id) {
        Detalle_Venta detalle = detalleVentaService.findById(id);
        return detalle != null ? ResponseEntity.ok(detalle) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Detalle_Venta> create(@RequestBody Detalle_Venta detalle) {
        return ResponseEntity.ok(detalleVentaService.save(detalle));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Detalle_Venta> update(@PathVariable int id, @RequestBody Detalle_Venta detalle) {
        Detalle_Venta existente = detalleVentaService.findById(id);
        if (existente != null) {
            detalle.setId_detalle(id);
            return ResponseEntity.ok(detalleVentaService.save(detalle));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Detalle_Venta existente = detalleVentaService.findById(id);
        if (existente != null) {
            detalleVentaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}