package com.cibertec.crud_service.repository;

import com.cibertec.crud_service.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
