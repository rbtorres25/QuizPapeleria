package com.example.ProyectoPostgres.Service;

import com.example.ProyectoPostgres.Model.Venta;
import com.example.ProyectoPostgres.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    public List<Venta> findAll() {
        return ventaRepository.findAll();
    }

    public Venta findById(int id) {
        return ventaRepository.findById(id).orElse(null);
    }

    public Venta save(Venta venta) {
        return ventaRepository.save(venta);
    }

    public void deleteById(int id) {
        ventaRepository.deleteById(id);
    }

    public List<Venta> getVentasPorEmpleado(int idEmpleado) {
        return ventaRepository.findVentasByEmpleado(idEmpleado);
    }

    public List<Venta> getVentasPorEmpleadoYCliente(int idEmpleado, int idCliente) {
        return ventaRepository.findVentasByEmpleadoAndCliente(idEmpleado, idCliente);
    }
}
