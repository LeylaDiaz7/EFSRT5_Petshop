package com.cibertec.crud_service.dto;

public record ProductoDto(
        Integer codigo,
        String nombre,
        Double precio,
        Integer stock,
        Integer categoriaId,
        String categoriaNombre
) {}

