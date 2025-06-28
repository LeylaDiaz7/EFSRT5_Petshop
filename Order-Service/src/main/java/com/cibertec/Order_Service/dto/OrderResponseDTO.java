package com.cibertec.Order_Service.dto;

import com.cibertec.Order_Service.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderResponseDTO(
        Integer ventaId,
        Integer usuarioId,
        LocalDateTime fecha,
        Double total,
        OrderStatus status,
        List<OrderItemDTO> items
) {
}
