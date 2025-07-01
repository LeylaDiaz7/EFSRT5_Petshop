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
    public Optional<Producto> obtenerProductoPorCodigo(Integer codigo) {
        return repository.findById(codigo);
    }

    @Override
    public Producto actualizarStock(Integer id, Integer nuevoStock) {
        Producto p = repository.findById(id).orElseThrow();
        p.setStock(nuevoStock);
        return repository.save(p);
    }

    @Override
    public void actualizarProducto(Producto producto) {
        repository.save(producto);
    }
}
