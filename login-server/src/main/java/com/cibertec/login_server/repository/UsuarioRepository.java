package com.cibertec.login_server.repository;

import com.cibertec.login_server.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByRoleIgnoreCase(String role);
}