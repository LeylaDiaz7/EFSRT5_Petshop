package com.cibertec.inventory_service.controller;

import com.cibertec.inventory_service.entity.Producto;
import com.cibertec.inventory_service.service.ProductoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Producto> listar() {
        return service.listarProductos();
    }

    @GetMapping("/{id}")
    public Producto obtener(@PathVariable Integer id) {
        return service.obtenerProductoPorId(id).orElseThrow();
    }

    @PutMapping("/{id}/stock")
    public Producto actualizarStock(@PathVariable Integer id, @RequestParam Integer cantidad) {
        return service.actualizarStock(id, cantidad);
    }
}
