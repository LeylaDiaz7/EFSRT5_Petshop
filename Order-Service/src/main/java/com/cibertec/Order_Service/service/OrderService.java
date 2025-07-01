package com.cibertec.Order_Service.service;

import com.cibertec.Order_Service.dto.OrderItemDTO;
import com.cibertec.Order_Service.dto.OrderRequestDTO;
import com.cibertec.Order_Service.dto.OrderResponseDTO;
import com.cibertec.Order_Service.entity.Order;
import com.cibertec.Order_Service.entity.OrderItem;
import com.cibertec.Order_Service.entity.OrderStatus;
import com.cibertec.Order_Service.exceptions.OrderNotFoundException;
import com.cibertec.Order_Service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderRequest) {
        // Validar stock con Inventory-Service
        orderRequest.items().forEach(item -> {
            if (item.productoId() == null || item.cantidad() == null) {
                throw new IllegalArgumentException("productoId y cantidad no pueden ser null");
            }
            inventoryClient.checkStock(item.productoId(), item.cantidad());
        });

        Order order = new Order();
        order.setUsuarioId(orderRequest.usuarioId());
        order.setFecha(LocalDateTime.now());
        order.setStatus(OrderStatus.PENDIENTE);

        List<OrderItem> items = orderRequest.items().stream()
                .map(item -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setProductoId(item.productoId());
                    orderItem.setCantidad(item.cantidad());
                    orderItem.setPrecioU(item.precioU());
                    orderItem.setSubtotal(item.precioU() * item.cantidad());
                    orderItem.setOrder(order);
                    return orderItem;
                }).collect(Collectors.toList());

        order.setItems(items);

        double total = items.stream()
                .mapToDouble(OrderItem::getSubtotal)
                .sum();

        order.setTotal(total);

        Order savedOrder = orderRepository.save(order);

        // Actualizar inventario
        items.forEach(item -> {
            if (item.getProductoId() == null || item.getCantidad() == null) {
                throw new IllegalArgumentException("productoId y cantidad no pueden ser null al actualizar stock");
            }
            inventoryClient.updateStock(item.getProductoId(), item.getCantidad());
        });

        return mapToOrderResponse(savedOrder);
    }


    public List<OrderResponseDTO> getOrdersByUser(Integer userId) {
        return orderRepository.findByUsuarioId(userId).stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

    public OrderResponseDTO getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));
        return mapToOrderResponse(order);
    }

    @Transactional
    public OrderResponseDTO cancelOrder(Integer orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        if(order.getStatus() == OrderStatus.COMPLETADA) {
            throw new IllegalStateException("No se puede cancelar una orden completada");
        }

        order.setStatus(OrderStatus.CANCELADA);

        // Devolver items al inventario
        order.getItems().forEach(item ->
                inventoryClient.updateStock(item.getProductoId(), -item.getCantidad()));

        Order updatedOrder = orderRepository.save(order);
        return mapToOrderResponse(updatedOrder);
    }

    private OrderResponseDTO mapToOrderResponse(Order order) {
        List<OrderItemDTO> itemDTOs = order.getItems().stream()
                .map(item -> new OrderItemDTO(
                        item.getProductoId(),
                        item.getCantidad(),
                        item.getPrecioU()
                ))
                .collect(Collectors.toList());

        return new OrderResponseDTO(
                order.getVentaId(),
                order.getUsuarioId(),
                order.getFecha(),
                order.getTotal(),
                order.getStatus(),
                itemDTOs
        );
    }

    public List<OrderResponseDTO> getOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::mapToOrderResponse)
                .collect(Collectors.toList());
    }

}
