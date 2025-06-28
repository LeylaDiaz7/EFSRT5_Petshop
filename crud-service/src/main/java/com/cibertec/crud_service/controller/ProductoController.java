package com.cibertec.crud_service.controller;

import com.cibertec.crud_service.dto.ProductoDto;
import com.cibertec.crud_service.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    // GET: Listar todos los productos
    @GetMapping
    public List<ProductoDto> listar() {
        return productoService.obtenerTodos();
    }

    // GET: Buscar producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> buscarPorId(@PathVariable Integer id) {
        ProductoDto producto = productoService.obtenerPorId(id);
        return ResponseEntity.ok(producto);
    }

    // POST: Crear un nuevo producto
    @PostMapping
    public ResponseEntity<String> crear(@RequestBody ProductoDto dto) {
        productoService.crear(dto);
        return ResponseEntity.ok("Producto agregado correctamente");
    }

    // PUT: Actualizar un producto existente
    @PutMapping("/{id}")
    public ResponseEntity<String> actualizar(@PathVariable Integer id, @RequestBody ProductoDto dto) {
        productoService.actualizar(id, dto);
        return ResponseEntity.ok("Producto actualizado correctamente");
    }

    // DELETE: Eliminar un producto por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        productoService.eliminar(id);
        return ResponseEntity.ok("Producto eliminado correctamente");
    }
}
