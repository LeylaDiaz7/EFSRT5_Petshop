package com.cibertec.Order_Service.dto;

public record OrderItemDTO(
        Integer productoId,
        Integer cantidad,
        Double precioU
) {
}
