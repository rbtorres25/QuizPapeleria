**# Proyecto Quiz Taller Papeleria

Este es un proyecto que gestiona información de ventas, detalles de venta, empleados, clientes y productos usando **Spring Boot** y **PostgreSQL**. En este proyecto se implementan consultas nativas para obtener los detalles de ventas realizadas por empleados a clientes específicos.

## Requisitos

- **PostgreSQL**
- **Java 21** o superior
- **Spring Boot 3.4.4**
- **Maven** (para la construcción del proyecto)

## Descripción

La aplicación permite gestionar ventas, productos y clientes. Además, se construyeron **consultas nativas** utilizando `@Query` para extraer datos específicos de la base de datos, como los detalles de las ventas realizadas por un empleado a un cliente. A continuación, se explica cómo se construyeron estas consultas nativas y cómo están implementadas en la aplicación.

## Estructura de la Base de Datos

La base de datos tiene varias tablas principales:

- **venta**: Guarda información sobre las ventas, como `id_venta`, `id_empleado`, `id_cliente`, `fecha`, `total`.
- **detalle_venta**: Guarda información sobre los detalles de cada venta, como `id_detalle`, `id_venta`, `id_producto`, `cantidad`, `precio_unitario`.
- **producto**: Guarda información sobre los productos, como `id_producto` y `nombre`.
- **empleado**: Información sobre los empleados que realizaron las ventas.
- **cliente**: Información sobre los clientes a quienes se les realizaron las ventas.

## Consultas en Estructura Swagger

**¿Qué es Swagger?**

Swagger es un framework de código abierto que permite a los desarrolladores diseñar, construir, documentar y consumir servicios web RESTful. Proporciona una forma sencilla y eficiente de definir la estructura de una API, especificando los endpoints disponibles, los parámetros necesarios, las respuestas esperadas y otros detalles importantes.

**Estructura Swagerr Proyecto Papeleria**

**Venta Controller** 

GET
```java
{
  "id_venta": 0,
  "fecha": 0,
  "cliente": {
    "id_cliente": 0,
    "nombre": "string",
    "cedula": "string",
    "telefono": "string",
    "correo": "string"
  },
  "empleado": {
    "id_empleado": 0,
    "nombre": "string",
    "cargo": "string",
    "telefono": "string"
  }
}
```

**DELETE** 


**PUT**

**POST**

/proveedores
Al abrir en el explorador http://localhost:8080/swagger-ui/index.html#/
API Supabase
1.0
OAS 3.0
/v3/api-docs
Documentacion de la API para gestionar BD ren Supabase

Contact Soporte API
Servers

http://localhost:8080 - Generated server url






## Consultas Nativas
### 1. Listen las ventas que ha realizado un empleado

La consulta nativa se define de la siguiente manera: SELECT * FROM venta WHERE id_empleado = :idEmpleado

Crear el método en el VentaRepository

```java
package com.example.ProyectoPostgres.Repository;

import com.example.ProyectoPostgres.Model.Venta;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Integer> {

    // Consulta nativa para obtener las ventas de un empleado
    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado", nativeQuery = true)
    List<Venta> findVentasByEmpleado(@Param("idEmpleado") int idEmpleado);
}



```
Ahora se debe Llama esta consulta desde el VentaService

```java
public List<Venta> getVentasPorEmpleado(int idEmpleado) {
    return ventaRepository.findVentasByEmpleado(idEmpleado);
}
```

Y desde el controlador llamado VentaController


```java
@GetMapping("/empleado/{idEmpleado}")
public ResponseEntity<List<Venta>> getVentasPorEmpleado(@PathVariable int idEmpleado) {
    List<Venta> ventas = ventaService.getVentasPorEmpleado(idEmpleado);
    if (ventas.isEmpty()) {
        return ResponseEntity.noContent().build();
    }
    return ResponseEntity.ok(ventas);
}
```

### Resultado el local:
```java

[
{
"id_venta": 2,
"fecha": 1744340819,
"cliente": {
"id_cliente": 1,
"nombre": "Rumi Torres",
"cedula": "123456789",
"telefono": "3101234567",
"correo": "juanrbtorres@example.com"
},
"empleado": {
"id_empleado": 1,
"nombre": "Carlos Mendoza",
"cargo": "Vendedor",
"telefono": "3209876543"
}
},
{
"id_venta": 100,
"fecha": 2020,
"cliente": {
"id_cliente": 2,
"nombre": "Rumi Torres",
"cedula": "123456789",
"telefono": "3101234567",
"correo": "juanrbtorres@example.com"
},
"empleado": {
"id_empleado": 1,
"nombre": "Carlos Mendoza",
"cargo": "Vendedor",
"telefono": "3209876543"
}
}
]
```


### 2. Cuales son los productos que ofrece un proveedor

Consulta nativa: productos por proveedor

```java
SELECT 
    p.id_producto,
    p.nombre AS nombre_producto,
    p.descripcion,
    p.precio,
    p.stock,
    pr.nombre AS nombre_proveedor
FROM 
    producto p
JOIN 
    proveedor pr ON p.id_proveedor = pr.id_proveedor
WHERE 
    pr.id_proveedor = 1;  -- Cambia el ID según el proveedor que desees consultar

```

ProductoRepository y agrega este método con una consulta nativa:

```java
@Query(value = "SELECT * FROM producto WHERE id_proveedor = :idProveedor", nativeQuery = true)
List<Producto> findByProveedor(@Param("idProveedor") int idProveedor);
```

Agrega el metodo  en los servicio ProductoService
```java
public List<Producto> getProductosPorProveedor(int idProveedor) {
return productoRepository.findByProveedor(idProveedor);
}
```

Ahora crea el endpoint en el controllador ProductoController

```java
@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping("/proveedor/{id}")
    public ResponseEntity<List<Producto>> getProductosPorProveedor(@PathVariable int id) {
        List<Producto> productos = productoService.getProductosPorProveedor(id);
        if (productos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(productos);
        }
    }
}
```

### Resultado 

http://localhost:8080/productos/proveedor/1
```java
[
{
"id_producto": 1,
"nombre": "Yogurt de Fresa",
"descripcion": "Yogurt natural con sabor a fresa",
"precio": 2.5,
"stock": 100,
"proveedor": {
"id_proveedor": 1,
"nombre": "Alpina",
"telefono": "3109876543",
"direccion": "Calle 123 #45-67",
"correo": "contacto@alpina.com"
}
},
{
"id_producto": 2,
"nombre": "Leche Entera",
"descripcion": "Leche entera en bolsa de 1L",
"precio": 3,
"stock": 200,
"proveedor": {
"id_proveedor": 1,
"nombre": "Alpina",
"telefono": "3109876543",
"direccion": "Calle 123 #45-67",
"correo": "contacto@alpina.com"
}
},
{
"id_producto": 3,
"nombre": "Queso Campesino",
"descripcion": "Queso fresco semiduro",
"precio": 5.5,
"stock": 50,
"proveedor": {
"id_proveedor": 1,
"nombre": "Alpina",
"telefono": "3109876543",
"direccion": "Calle 123 #45-67",
"correo": "contacto@alpina.com"
}
}

```

### Cuales son las ventas que ha realizado un empleado a un cliente

Consulta SQL Nativa
```java
SELECT *
FROM venta
WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente;
```

Se crea el método con @Query en tu interfaz  VentaRepository
```java
package com.example.ProyectoPostgres.Repository;

import com.example.ProyectoPostgres.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    @Query(value = "SELECT * FROM venta WHERE id_empleado = :idEmpleado AND id_cliente = :idCliente", nativeQuery = true)
    List<Venta> findByEmpleadoAndCliente(@Param("idEmpleado") int idEmpleado, @Param("idCliente") int idCliente);

}
```
Metodo en VentaService

```java
public List<Venta> getVentasPorEmpleadoYCliente(int idEmpleado, int idCliente) {
return ventaRepository.findByEmpleadoAndCliente(idEmpleado, idCliente);
}

```
nuevo endpoint VentaController
```java
@GetMapping("/empleado/{idEmpleado}/cliente/{idCliente}")
public ResponseEntity<List<Venta>> getVentasPorEmpleadoYCliente(@PathVariable int idEmpleado, @PathVariable int idCliente) {
List<Venta> ventas = ventaService.getVentasPorEmpleadoYCliente(idEmpleado, idCliente);
if (ventas.isEmpty()) {
return ResponseEntity.noContent().build();
} else {
return ResponseEntity.ok(ventas);
}
}
```

### Resultado 

http://localhost:8080/ventas/empleado/1/cliente/2
```java
[
{
"id_venta": 100,
"fecha": 2020,
"cliente": {
"id_cliente": 2,
"nombre": "Rumi Torres",
"cedula": "123456789",
"telefono": "3101234567",
"correo": "juanrbtorres@example.com"
},
"empleado": {
"id_empleado": 1,
"nombre": "Carlos Mendoza",
"cargo": "Vendedor",
"telefono": "3209876543"
}
}

```

### 4. **Mostrar cual es el detalle de venta que han echo un empleado a un cliente

La consulta nativa para obtener las ventas realizadas por un empleado a un cliente específico es la siguiente:

```java
@Query(value = "SELECT " +
               "v.id_venta, " +
               "v.fecha, " +
               "v.total, " +
               "dv.id_detalle, " +
               "dv.cantidad, " +
               "dv.precio_unitario, " +
               "(dv.cantidad * dv.precio_unitario) AS subtotal, " +
               "p.nombre AS nombre_producto " +
               "FROM venta v " +
               "JOIN detalle_venta dv ON v.id_venta = dv.id_venta " +
               "JOIN producto p ON dv.id_producto = p.id_producto " +
               "WHERE v.id_empleado = :idEmpleado AND v.id_cliente = :idCliente", nativeQuery = true)
List<Object[]> findDetalleVentaPorEmpleadoYCliente(@Param("idEmpleado") int idEmpleado, @Param("idCliente") int idCliente);

```
### AUTOR RUMI TORRES 

