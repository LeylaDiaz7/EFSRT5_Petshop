package com.cibertec.Order_Service.dto;

import java.util.List;

public record OrderRequestDTO (Integer usuarioId,
                               List<OrderItemDTO> items
) {
}
