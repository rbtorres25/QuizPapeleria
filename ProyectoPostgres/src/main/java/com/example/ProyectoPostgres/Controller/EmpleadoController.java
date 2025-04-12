package com.example.ProyectoPostgres.Controller;

import com.example.ProyectoPostgres.Model.Empleado;
import com.example.ProyectoPostgres.Service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empleados")
@CrossOrigin(origins = "*")
public class EmpleadoController {
    @Autowired
    private EmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<Empleado>> getAll() {
        return ResponseEntity.ok(empleadoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empleado> getById(@PathVariable int id) {
        Empleado empleado = empleadoService.findById(id);
        return empleado != null ? ResponseEntity.ok(empleado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Empleado> create(@RequestBody Empleado empleado) {
        return ResponseEntity.ok(empleadoService.save(empleado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Empleado> update(@PathVariable int id, @RequestBody Empleado empleado) {
        Empleado existente = empleadoService.findById(id);
        if (existente != null) {
            empleado.setId_empleado(id);
            return ResponseEntity.ok(empleadoService.save(empleado));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Empleado existente = empleadoService.findById(id);
        if (existente != null) {
            empleadoService.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
