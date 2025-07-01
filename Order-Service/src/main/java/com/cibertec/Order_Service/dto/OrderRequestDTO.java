package com.cibertec.Order_Service.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequestDTO(
        @NotNull Integer usuarioId,
        @NotEmpty List<@Valid OrderItemDTO> items
) {}