package com.example.ProyectoPostgres.Controller;

import com.example.ProyectoPostgres.Model.Venta;
import com.example.ProyectoPostgres.Service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin(origins = "*")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<Venta>> getAll() {
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Venta> getById(@PathVariable int id) {
        Venta venta = ventaService.findById(id);
        return venta != null ? ResponseEntity.ok(venta) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Venta> create(@RequestBody Venta venta) {
        return ResponseEntity.ok(ventaService.save(venta));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Venta> update(@PathVariable int id, @RequestBody Venta venta) {
        Venta existente = ventaService.findById(id);
        if (existente != null) {
            venta.setId_venta(id);
            return ResponseEntity.ok(ventaService.save(venta));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Venta existente = ventaService.findById(id);
        if (existente != null) {
            ventaService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/empleado/{idEmpleado}")
    public ResponseEntity<List<Venta>> getVentasPorEmpleado(@PathVariable int idEmpleado) {
        List<Venta> ventas = ventaService.getVentasPorEmpleado(idEmpleado);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(ventas);
    }
    @GetMapping("/empleado/{idEmpleado}/cliente/{idCliente}")
    public ResponseEntity<List<Venta>> getVentasPorEmpleadoYCliente(@PathVariable int idEmpleado, @PathVariable int idCliente) {
        List<Venta> ventas = ventaService.getVentasPorEmpleadoYCliente(idEmpleado, idCliente);
        if (ventas.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(ventas);
        }
    }
}
