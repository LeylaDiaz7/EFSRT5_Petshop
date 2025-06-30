package com.cibertec.inventory_service.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "producto")
@Data
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String nombre;

    private Double precio;

    private Integer stock;

    @Column(name = "categoria_id")
    private Integer categoriaId;
}
