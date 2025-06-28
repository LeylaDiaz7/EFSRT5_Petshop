package com.cibertec.crud_service.service.impl;

import com.cibertec.crud_service.dto.ProductoDto;
import com.cibertec.crud_service.entity.Categoria;
import com.cibertec.crud_service.entity.Producto;
import com.cibertec.crud_service.repository.CategoriaRepository;
import com.cibertec.crud_service.repository.ProductoRepository;
import com.cibertec.crud_service.service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    @Override
    public List<ProductoDto> obtenerTodos() {
        return productoRepository.findAll().stream()
                .map(p -> new ProductoDto(
                        p.getCodigo(),
                        p.getNombre(),
                        p.getPrecio(),
                        p.getStock(),
                        p.getCategoria().getCategoriaId(),
                        p.getCategoria().getCategoriaNombre()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDto obtenerPorId(Integer id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Producto no encontrado"));
        return new ProductoDto(
                p.getCodigo(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock(),
                p.getCategoria().getCategoriaId(),
                p.getCategoria().getCategoriaNombre()
        );
    }

    @Override
    public ProductoDto crear(ProductoDto dto) {
        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "❌ Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setCategoria(categoria);

        productoRepository.save(producto);

        return new ProductoDto(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                categoria.getCategoriaId(),
                categoria.getCategoriaNombre()
        );
    }

    @Override
    public ProductoDto actualizar(Integer id, ProductoDto dto) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Producto no encontrado"));

        Categoria categoria = categoriaRepository.findById(dto.categoriaId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "❌ Categoría no encontrada"));

        producto.setNombre(dto.nombre());
        producto.setPrecio(dto.precio());
        producto.setStock(dto.stock());
        producto.setCategoria(categoria);

        productoRepository.save(producto);

        return new ProductoDto(
                producto.getCodigo(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getStock(),
                categoria.getCategoriaId(),
                categoria.getCategoriaNombre()
        );
    }

    @Override
    public void eliminar(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "❌ Producto no encontrado");
        }
        productoRepository.deleteById(id);
    }
}
