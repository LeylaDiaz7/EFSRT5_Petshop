package com.cibertec.crud_service.service;

import com.cibertec.crud_service.dto.ProductoDto;

import java.util.List;

public interface ProductoService {
    List<ProductoDto> obtenerTodos();
    ProductoDto obtenerPorId(Integer id);
    ProductoDto crear(ProductoDto dto);
    ProductoDto actualizar(Integer id, ProductoDto dto);
    void eliminar(Integer id);
}
