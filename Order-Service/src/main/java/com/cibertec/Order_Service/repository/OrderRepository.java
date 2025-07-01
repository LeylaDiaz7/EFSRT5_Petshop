package com.cibertec.Order_Service.repository;

import com.cibertec.Order_Service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUsuarioId(Integer usuarioId);
}
