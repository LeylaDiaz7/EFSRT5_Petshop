package com.cibertec.inventory_service.service;

import com.cibertec.inventory_service.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {
    List<Producto> listarProductos();
    Optional<Producto> obtenerProductoPorId(Integer id);
    Optional<Producto> obtenerProductoPorCodigo(Integer codigo);
    Producto actualizarStock(Integer id, Integer nuevoStock);
    void actualizarProducto(Producto producto);
}
