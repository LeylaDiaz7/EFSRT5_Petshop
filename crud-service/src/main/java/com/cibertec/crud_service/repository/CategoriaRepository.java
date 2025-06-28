package com.cibertec.crud_service.repository;

import com.cibertec.crud_service.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    Optional<Categoria> findByCategoriaNombre(String categoriaNombre);
}
