package com.cibertec.Order_Service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "detalle_venta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer detalleId;

    private Integer productoId;
    private Integer cantidad;
    private Double precioU;
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "ventaId")
    private Order order;
}
