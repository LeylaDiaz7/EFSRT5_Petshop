package com.cibertec.inventory_service.repository;

import com.cibertec.inventory_service.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
