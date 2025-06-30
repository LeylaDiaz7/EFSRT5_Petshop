package com.cibertec.login_server.controller;

import com.cibertec.login_server.entity.Usuario;
import com.cibertec.login_server.service.UsuarioService;
import com.cibertec.login_server.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/admin")
    public String admin() {
        return "Bienvenido ADMIN";
    }

    @GetMapping("/cliente")
    public String cliente() {
        return "Bienvenido CLIENTE";
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> listarClientes(Authentication authentication) {
        String username = authentication.getName();

        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElse(null);

        if (usuario == null || !"ADMIN".equalsIgnoreCase(usuario.getRole())) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("Solo el Admin tiene acceso a la lista de clientes.");
        }

        List<Usuario> clientes = usuarioService.obtenerClientes();
        return ResponseEntity.ok(clientes);
    }
}
