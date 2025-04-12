package com.example.ProyectoPostgres.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.mapping.List;
import org.w3c.dom.Text;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_producto;
    private String nombre;
    private String descripcion; // a String
    private double precio; //
    private int stock;

    @ManyToOne
    @JoinColumn(name = "id_proveedor")
    private Proveedor proveedor;

   // @OneToMany(mappedBy = "producto") //
  //  private List<Detalle_Venta> detalles; // va a List<Detalle_Venta>
}


//