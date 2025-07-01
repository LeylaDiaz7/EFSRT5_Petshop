package com.cibertec.Order_Service.controller;

import com.cibertec.Order_Service.dto.OrderRequestDTO;
import com.cibertec.Order_Service.dto.OrderResponseDTO;
import com.cibertec.Order_Service.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrdenVistaController {

    private final OrderService orderService;

    public OrdenVistaController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/ordenes")
    public String listarOrdenes(Model model,
                                @RequestParam(required = false) Integer usuarioId,
                                @RequestParam(required = false) String estado,
                                @RequestParam(required = false) Integer ventaId) {

        List<OrderResponseDTO> ordenes = orderService.getOrders(); // todas por defecto

        // Si se filtró por ventaId (prioridad más alta)
        if (ventaId != null) {
            try {
                ordenes = List.of(orderService.getOrderById(ventaId));
            } catch (Exception e) {
                ordenes = List.of(); // no encontrada, lista vacía
            }
        }
        // Si no, se puede filtrar por usuarioId
        else if (usuarioId != null) {
            ordenes = orderService.getOrdersByUser(usuarioId);
        }

        // Si también se filtró por estado
        if (estado != null && !estado.isBlank()) {
            String estadoFiltrado = estado.toUpperCase();
            ordenes = ordenes.stream()
                    .filter(o -> o.status().name().equalsIgnoreCase(estadoFiltrado))
                    .toList();
        }

        model.addAttribute("ordenes", ordenes);
        return "ordenes";
    }

    @GetMapping("/orden/crear")
    public String mostrarFormularioOrden(Model model) {
        OrderRequestDTO orderRequest = new OrderRequestDTO(null, new ArrayList<>());
        model.addAttribute("orderRequest", orderRequest);
        return "orden-crear"; // thymeleaf buscará orden-crear.html
    }

    @PostMapping("/ordenes/{ventaId}/cancelar")
    public String cancelarOrden(@PathVariable Integer ventaId) {
        orderService.cancelOrder(ventaId);
        return "redirect:/ordenes";
    }

}
