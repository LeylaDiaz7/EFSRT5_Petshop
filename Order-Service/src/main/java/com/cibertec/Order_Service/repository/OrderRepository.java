package com.cibertec.Order_Service.repository;

import com.cibertec.Order_Service.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findByUsuarioId(Integer usuarioId);

    //List<Order> findByUsuarioIdOrderByOrderDateDesc(Integer usuarioId);

    /*
    public interface OrderRepository extends JpaRepository<Order, Integer> {
        @Query("SELECT o FROM Order o WHERE o.usuarioId = :usuarioId")
        List<Order> findByUsuarioId(@Param("usuarioId") Integer usuarioId);

        // Versión con ordenación
        @Query("SELECT o FROM Order o WHERE o.usuarioId = :usuarioId ORDER BY o.orderDate DESC")
        List<Order> findByUsuarioIdOrderedByDate(@Param("usuarioId") Integer usuarioId);
    } */
}
