package com.cibertec.inventory_service.service;

import com.cibertec.inventory_service.entity.Producto;
import com.cibertec.inventory_service.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoServiceImp implements ProductoService {

    private final ProductoRepository repository;

    public ProductoServiceImp(ProductoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Producto> listarProductos() {
        return repository.findAll();
    }

    @Override
    public Optional<Producto> obtenerProductoPorId(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Producto actualizarStock(Integer id, Integer cantidad) {
        Producto p = repository.findById(id).orElseThrow();
        int nuevoStock = p.getStock() + cantidad;
        if (nuevoStock < 0) {
            throw new IllegalArgumentException("Stock no puede ser negativo");
        }
        p.setStock(nuevoStock);
        return repository.save(p);
    }


    public void verificarStock(Integer productoId, Integer cantidadSolicitada) {
        Producto producto = obtenerProductoPorId(productoId).orElseThrow();
        if (producto.getStock() < cantidadSolicitada) {
            throw new IllegalStateException("Stock insuficiente para el producto con ID " + productoId);
        }
    }

}
