package com.cibertec.Order_Service.dto;

import jakarta.validation.constraints.NotNull;

public record OrderItemDTO(
        @NotNull Integer productoId,
        @NotNull Integer cantidad,
        @NotNull Double precioU
) {}
