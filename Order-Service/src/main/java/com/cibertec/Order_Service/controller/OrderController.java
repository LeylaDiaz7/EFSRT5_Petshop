package com.cibertec.Order_Service.controller;


import com.cibertec.Order_Service.dto.OrderRequestDTO;
import com.cibertec.Order_Service.dto.OrderResponseDTO;
import com.cibertec.Order_Service.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderResponseDTO> createOrder(@Valid @RequestBody OrderRequestDTO orderRequest) {
        return ResponseEntity.ok(orderService.createOrder(orderRequest));
    }

    @GetMapping("/user/{usuarioId}")
    public ResponseEntity<List<OrderResponseDTO>> getOrdersByUser(@PathVariable Integer usuarioId) {
        return ResponseEntity.ok(orderService.getOrdersByUser(usuarioId));
    }

    @GetMapping("/{ventaId}")
    public ResponseEntity<OrderResponseDTO> getOrderById(@PathVariable Integer ventaId) {
        return ResponseEntity.ok(orderService.getOrderById(ventaId));
    }

    @PutMapping("/{ventaId}/cancel")
    public ResponseEntity<OrderResponseDTO> cancelOrder(@PathVariable Integer ventaId) {
        return ResponseEntity.ok(orderService.cancelOrder(ventaId));
    }
    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrders() {
        return ResponseEntity.ok(orderService.getOrders());
    }
}
